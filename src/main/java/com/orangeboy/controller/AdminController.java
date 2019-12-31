package com.orangeboy.controller;

import com.orangeboy.pojo.Admin;
import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.Student;
import com.orangeboy.service.AdminService;
import com.orangeboy.service.GroupService;
import com.orangeboy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public String admin(HttpSession session, Model model, HttpServletResponse response) throws IOException {
        Admin admin=(Admin)session.getAttribute("admin");
        admin.setNewLoginTime();
        adminService.updateAdmin(admin);
        model.addAttribute("lastTime",session.getAttribute("adminLastTime"));
        model.addAttribute("group",admin.getGroup());
        model.addAttribute("goodStudents",adminService.getCompletedStudents(admin));
        model.addAttribute("studentsList",adminService.getAllStudents(admin));
        return "admin";
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
        session.setAttribute("goodStudents",null);
        admin.getGroup().setNewStartTime();
        groupService.updateGroup(admin.getGroup());
        return "true";
    }

    @RequestMapping("/deleteStudent")
    @ResponseBody
    public String deleteStudent(String id,HttpSession session){
        Admin admin=adminService.getAdminFromSession(session);
        Student student=studentService.queryStudentById(id,admin.getGroup());
        if(student!=null){
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
        admin.setPassword(newPassword);
        adminService.changeAdminPassword(admin);
        return "true";
    }

    @RequestMapping("/changeInfo")
    @ResponseBody
    public String chagneInfo(String groupName,String groupSec,HttpSession session){
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
        System.out.println("id"+student.getId());
        Admin admin=adminService.getAdminFromSession(session);
        Student sameStudent=studentService.queryStudentById(student.getId(),admin.getGroup());
        if(sameStudent==null){
            adminService.addStudent(student,admin);
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
            studentService.addStudent(student);
            completedStudents.add(student);
        }

        return new addMutipleData(sameIdStudents,completedStudents);
    }

    @RequestMapping("/addStudents")
    public String getAddStudents(){
        return "addMutiple";
    }
}
