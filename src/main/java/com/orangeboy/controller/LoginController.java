package com.orangeboy.controller;

import com.orangeboy.pojo.Admin;
import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.Student;
import com.orangeboy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
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
    public String index(Model model){
        emailService.sendEmail("chenenhan@qq.com","hahahahaha","this is a test mail hahahahahaha!");
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
        return "index";
    }

    @RequestMapping("/Login")
    public String login(Student student, String groupSec, HttpSession session,Model model){
        Group group=null;
        try {
            group = (Group) session.getAttribute("group");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("1111111111111111111111");
        System.out.println(group==null);
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
                    model.addAttribute("info","没有找到班级");
                    return "fail";
                }

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
                model.addAttribute("info","密码错误");
                return "fail";
            }
        }
        System.out.println(student==null);
        System.out.println(student.getId());
        System.out.println(student.getName());

        student.setGroupId(group.getGroupId());
        Student validStudent = studentService.queryValidStudent(student,group);
        System.out.println(validStudent==null);
        if(validStudent!=null){
            if(validStudent.isCompleteState()){
                model.addAttribute("info","你已经提交过了");
                return "fail";
            }
            else if(!validStudent.isRequireState()){
                model.addAttribute("info","你有特权不用做哦");
                return "fail";
            }
            else {
                studentService.setStudentCompleted(student);
                session.setAttribute("student", student);
                model.addAttribute("rank", studentService.getStudentRank(student));
                return "success";
            }
        }
        else{
            model.addAttribute("info","没有这个学生");
            return "fail";
        }
    }

    @RequestMapping("/rankTable")
    public String getRankTable(HttpSession session,Model model){
        Group group=(Group)session.getAttribute("group");
        if(group!=null){
            List<Student> badStudents=studentService.getNotCompletedRequiredStudents(group);
            List<Student> goodStudents=studentService.getCompletedStudents(group);
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

}
