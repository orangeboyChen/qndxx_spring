package com.orangeboy.controller;

import com.orangeboy.pojo.Admin;
import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.Student;
import com.orangeboy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.http.HttpResponse;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private GroupService groupService;


    @RequestMapping("/")
    public String index(HttpSession session, Model model){
//        List<Student> badStudents=studentService.getNotCompletedRequiredStudents();
//        List<Student> goodStudents=studentService.getCompletedStudents();
//        Student[] topGoodStudents=new Student[3];
//        for(Student s : goodStudents){
//            if(s.getRank()==1)topGoodStudents[0]=s;
//            if(s.getRank()==2)topGoodStudents[1]=s;
//            if(s.getRank()==3)topGoodStudents[2]=s;
//        }
//        model.addAttribute("goodStudents",topGoodStudents);
//        model.addAttribute("badStudents",badStudents);
//        model.addAttribute("goodStudentsCount",goodStudents.size());
//        model.addAttribute("badStudentsCount",badStudents.size());
        Student student = (Student) session.getAttribute("student");
        if(student!=null){
            model.addAttribute("groupSec",student.getGroupSec());
        }
        else {
            Student hasDoneStudent = (Student) session.getAttribute("hasDoneStudent");
            if(hasDoneStudent!=null){
                model.addAttribute("groupSec",hasDoneStudent.getGroupSec());
            }
        }
        return "index";
    }

    @RequestMapping("/Login")
    public String login(Student student, String groupSec, HttpSession session,Model model){
        if(student==null||groupSec==null) {
            model.addAttribute("msg","别皮了哦");
            return "fail";
        }
        if("superadmin".equals(student.getId())&&"superadmin".equals(student.getName())){
            session.setAttribute("superAdmin","true");
            return "redirect:/superAdmin";
        }


        Group group=null;
        try {
            group = (Group) session.getAttribute("group");
        }catch (Exception e){
            e.printStackTrace();
        }

        if(group==null){
            if(!"".equals(groupSec.trim())){
                group=groupService.queryGroupBySec(groupSec);
                System.out.println(group==null);
                if(group!=null) {
                    session.setAttribute("group", group);
                    System.out.println(group.getGroupId());
                    group.setTimeStr();
                }
                else {
                    session.setAttribute("student", null);
                    model.addAttribute("info","没有找到班级");
                    return "fail";
                }
            }
            else{
                session.setAttribute("student", null);
                model.addAttribute("info","别皮哦");
                return "fail";
            }
        }

        if("admin".equals(student.getId())){
            Admin admin=new Admin(student.getGroupId(),student.getId(),student.getName());
            Admin validAdmin=adminService.getValidAdmin(admin);
            if(validAdmin!=null){
                validAdmin.setGroup(group);
                group.setSchoolObject(schoolService.querySchoolByGroup(group));
                session.setAttribute("adminLastTime",validAdmin.getTimeStr());
                session.setAttribute("admin",validAdmin);
                System.out.println(session.getId());
                return "redirect:/admin";
            }
            else{
                session.setAttribute("student", null);
                model.addAttribute("info","密码错误");
                return "fail";
            }
        }

        student.setGroupId(group.getGroupId());
        Student validStudent = studentService.queryValidStudent(student,group);
        System.out.println(validStudent==null);
        if(validStudent!=null){
            if(validStudent.isCompleteState()){
                student.setGroupSec(groupSec);
                session.setAttribute("hasDoneStudent", student);
                model.addAttribute("info","你已经提交过了");
                return "fail";
            }
            else if(!validStudent.isRequireState()){
                student.setGroupSec(groupSec);
                session.setAttribute("hasDoneStudent", student);
                model.addAttribute("info","你有特权不用做哦");
                return "fail";
            }
            else {
                if(session.getAttribute("hasDoneStudent")!=null){
                    Student hasDoneStudent = (Student) session.getAttribute("hasDoneStudent");
                    model.addAttribute("info","你和"+(hasDoneStudent.getName()+"有什么关系吗"));
                    return "fail";
                }
                student.setGroupSec(groupSec);
                session.setAttribute("student",student);
                return "upload";
            }
        }
        else{
            if("".equals(student.getId().trim())||"".equals(student.getId().trim())){
                model.addAttribute("info","你在逗我呢");
            }
            else {
                model.addAttribute("info", "没有这个学生");
            }
            session.setAttribute("student", null);
            return "fail";
        }
    }

    @RequestMapping("/rankTable")
    public String getRankTable(HttpSession session,Model model){
        Group group=(Group)session.getAttribute("group");
        if(group!=null){
            List<Student> badStudents=studentService.queryNotCompletedRequiredStudents(group);
            List<Student> goodStudents=studentService.queryCompletedStudents(group);
            Student[] topGoodStudents=new Student[3];
            for(Student s : goodStudents){
                if(s.getRank()==1)topGoodStudents[0]=s;
                if(s.getRank()==2)topGoodStudents[1]=s;
                if(s.getRank()==3)topGoodStudents[2]=s;
            }
            model.addAttribute("goodStudents",topGoodStudents);
            model.addAttribute("badStudents",badStudents);
            model.addAttribute("goodStudentsCount",goodStudents.size());
            model.addAttribute("badStudentsCount",badStudents.size());
            return "index-ajax/rank-table";
        }
        else{
            return "blank";
        }
    }

    @RequestMapping("/indexRight")
    public String getIndexRight(){
        return "index-ajax/index-right";
    }

    @RequestMapping("/upload")
    public String up(){
        return "upload";
    }

}
