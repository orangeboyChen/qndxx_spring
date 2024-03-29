package com.orangeboy.controller;

import com.orangeboy.constant.FileConstant;
import com.orangeboy.pojo.Admin;
import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.Student;
import com.orangeboy.service.AdminService;
import com.orangeboy.service.GroupService;
import com.orangeboy.service.StudentService;

import static com.orangeboy.constant.FileConstant.PATH;
import static com.orangeboy.constant.FileConstant.TEMP_PATH;
import static com.orangeboy.constant.SessionConstant.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AdminService adminService;

    @RequestMapping("")
    public String admin(HttpSession session, Model model) throws IOException {
        Admin admin=(Admin)session.getAttribute(ADMIN);
        admin.setNewLoginTime();
        adminService.updateAdmin(admin);
        model.addAttribute("lastTime",session.getAttribute("adminLastTime"));
        model.addAttribute("group",admin.getGroup());
        model.addAttribute("goodStudents",adminService.queryCompletedStudents(admin));
        model.addAttribute("studentsList",adminService.getAllStudents(admin));
        return "admin/adminBase";
    }

    @RequestMapping("/setSaying")
    @ResponseBody
    public String setSaying(String saying,HttpSession session){
        Admin admin=adminService.getAdminFromSession(session);
        if("".equals(saying.trim())){
            admin.getGroup().setSaying("无");
        }
        else{
            admin.getGroup().setSaying(saying);
        }
        groupService.updateGroup(admin.getGroup());
        return "true";
    }

    @RequestMapping("/newTerm")
    @ResponseBody
    public String setNewTerm(String saying,HttpSession session){
        Admin admin=adminService.getAdminFromSession(session);
        setSaying(saying,session);
        adminService.initStudentAllCompleteState(admin);
        admin.getGroup().setNewStartTime();
        groupService.updateGroup(admin.getGroup());
        session.setAttribute(GOOD_STUDENTS,null);
        admin.getGroup().setNewStartTime();
        groupService.updateGroup(admin.getGroup());

        List<Student> students = adminService.getAllStudents(admin);
        for (Student student:students) {
            String picName = student.getPicName();
            if(picName==null||"".equals(picName.trim())) continue;
            File file = new File(PATH+"/"+picName);
            file.delete();
        }

        return "true";
    }

    @RequestMapping("/deleteStudent")
    @ResponseBody
    public String deleteStudent(String id,HttpSession session){
        Admin admin=adminService.getAdminFromSession(session);
        Student student=studentService.queryStudentById(id,admin.getGroup());
        if(student!=null){
            String picName = student.getPicName();
            if(!(picName==null||"".equals(picName.trim()))){
                File file = new File(PATH+"/"+picName);
                file.delete();
            }
            studentService.removeStudent(student);
            return "true";
        }
        else {
            return "false";
        }
    }

    @RequestMapping("/changeRequireStateToYes")
    @ResponseBody
    public String changeRequireStateToYes(String id,HttpSession session){
        Admin admin=adminService.getAdminFromSession(session);
        Student student=studentService.queryStudentById(id,admin.getGroup());
        if(student!=null){
            adminService.setStudentRequireState(student,true);
            return "true";
        }
        else{
            return "false";
        }
    }

    @RequestMapping("/changeRequireStateToNo")
    @ResponseBody
    public String changeRequireStateToNo(String id,HttpSession session){
        Admin admin=adminService.getAdminFromSession(session);
        Student student=studentService.queryStudentById(id,admin.getGroup());
        if(student!=null){
            adminService.setStudentRequireState(student,false);
            return "true";
        }
        else{
            return "false";
        }
    }

    @RequestMapping("/changePassword")
    @ResponseBody
    public String changePassword(String oldPassword,String newPassword,HttpSession session){
        Admin admin=adminService.getAdminFromSession(session);
        if(!admin.getPassword().equals(oldPassword)) return "false";
        if(newPassword.length()<8||newPassword.length()>16) return "false";
        admin.setPassword(newPassword);
        adminService.changeAdminPassword(admin);
        return "true";
    }

    @RequestMapping("/changeInfo")
    @ResponseBody
    public String changeInfo(String groupName, String groupSec, HttpSession session){
        Admin admin=adminService.getAdminFromSession(session);
         Group group=admin.getGroup();
         //group.setGroupName(groupName);
         group.setGroupSec(groupSec);
         groupService.updateGroup(group);
         return "true";

    }

    @RequestMapping("/initAllRequest")
    @ResponseBody
    public String initAllRequest(HttpSession session){
        Admin admin=adminService.getAdminFromSession(session);
        adminService.initStudentAllRequireState(admin);
        return "true";
    }

    @RequestMapping("/checkPassword")
    @ResponseBody
    public String checkPassword(String password,HttpSession session){
        Admin admin=adminService.getAdminFromSession(session);
        if(admin.getPassword().equals(password)){
            return "true";
        }
        else{
            return "false";
        }
    }

    @RequestMapping("/addStudent")
    @ResponseBody
    public String addStudent(Student student,HttpSession session){
        Admin admin=adminService.getAdminFromSession(session);
        Student sameStudent=studentService.queryStudentById(student.getId(),admin.getGroup());
        if(sameStudent==null){
            adminService.insertStudent(student,admin);
            return "true";
        }
        else{
            return "false";
        }
    }

    @RequestMapping("/sameIdCheck")
    @ResponseBody
    public String sameIdCheck(String id,HttpSession session){
        Admin admin=adminService.getAdminFromSession(session);
        Student sameStudent=studentService.queryStudentById(id,admin.getGroup());
        if(sameStudent==null){
            return "true";
        }
        else {
            return "false";
        }
    }


    class addMutipleData{
        List<Student> sameIdStudents;
        List<Student> completedStudents;
        addMutipleData(List<Student> sameIdStudents, List<Student> completedStudents){
            this.sameIdStudents=sameIdStudents;
            this.completedStudents=completedStudents;
        }

        public List<Student> getSameIdStudents() {
            return sameIdStudents;
        }

        public void setSameIdStudents(List<Student> sameIdStudents) {
            this.sameIdStudents = sameIdStudents;
        }

        public List<Student> getCompletedStudents() {
            return completedStudents;
        }

        public void setCompletedStudents(List<Student> completedStudents) {
            this.completedStudents = completedStudents;
        }
    }

    @RequestMapping("/addMutiple")
    @ResponseBody
    public addMutipleData addMutiple(String[] ids, String[] names, HttpSession session){
        Admin admin=adminService.getAdminFromSession(session);
        List<Student> sameIdStudents=new ArrayList<Student>();
        List<Student> completedStudents=new ArrayList<Student>();
        for(int i=0;i<ids.length;i++){
            Student sameStudent=studentService.queryStudentById(ids[i],admin.getGroup());
            if(sameStudent!=null){
                sameIdStudents.add(new Student(ids[i],names[i],admin.getGroup()));
                continue;
            }
            Student student=new Student(ids[i],names[i],admin.getGroup());
            studentService.insertStudent(student);
            completedStudents.add(student);
        }

        return new addMutipleData(sameIdStudents,completedStudents);
    }

    @RequestMapping("/addStudents")
    public String getAddStudents(){
        return "addMutiple";
    }


    @RequestMapping("/init")
    @ResponseBody
    public String initStudents(HttpSession session){
        Admin admin=adminService.getAdminFromSession(session);
        adminService.removeGroupStudents(admin.getGroup());
        return "true";
    }

    @RequestMapping("/download/{id}")
    public String download(@PathVariable("id")String id, HttpSession session, HttpServletResponse response) throws IOException {
        Student student = studentService.queryStudentById(id,((Admin)session.getAttribute(ADMIN)).getGroup());
        String picName = student.getPicName();
        String path = PATH + picName;

        response.reset();
        response.setHeader("Content-Disposition","attachment;fileName="
                + URLEncoder.encode(student.getName()
                +"-"+student.getId()
                +"的截图"+"."+student.getPicName().substring(student.getPicName().lastIndexOf('.') + 1), "UTF-8"));
        File file = new File(path);
        InputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = response.getOutputStream();
        byte[] b = new byte[1024];
        int index = 0;
        while((index = inputStream.read(b)) != -1){
            outputStream.write(b,0,index);
            outputStream.flush();
        }
        outputStream.close();
        inputStream.close();
        return null;
    }

    @RequestMapping("/logOut")
    @ResponseBody
    public String logOut(HttpSession session){
        session.setAttribute(ADMIN,null);

        return null;
    }

    @RequestMapping("/downloadAll")
    public String downloadAll(HttpSession session, HttpServletResponse response) throws IOException {
        Admin admin = (Admin) session.getAttribute(ADMIN);
        List<Student> students = adminService.queryCompletedStudents(admin);

        String zipName = UUID.randomUUID().toString() + ".zip";

        File tempDir = new File(TEMP_PATH);
        if(!tempDir.exists()) tempDir.mkdir();
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(TEMP_PATH + zipName));
        for (Student student:students) {
            InputStream inputStream = new FileInputStream(PATH + student.getPicName());
            zipOutputStream.putNextEntry(new ZipEntry(
                    student.getName()
                            +"-"+student.getId()
                            +"的截图"+"."+student.getPicName().substring(student.getPicName().lastIndexOf('.') + 1)
            ));
            int temp = 0;
            while((temp = inputStream.read())!=-1){
                zipOutputStream.write(temp);
            }
            inputStream.close();
        }
        zipOutputStream.close();


        response.reset();
        response.setHeader("Content-Disposition","attachment;fileName="
                + URLEncoder.encode(admin.getGroup().getGroupName()
                + "的全部截图("
                + studentService.getCompletedCount(admin.getGroup())
                + "张).zip"
                , "UTF-8"));

        File file = new File(TEMP_PATH + zipName);
        InputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = response.getOutputStream();
        byte[] b = new byte[1024];
        int index = 0;
        while((index = inputStream.read(b)) != -1){
            outputStream.write(b,0,index);
            outputStream.flush();
        }
        outputStream.close();
        inputStream.close();
        file.delete();
        return null;
    }

}
