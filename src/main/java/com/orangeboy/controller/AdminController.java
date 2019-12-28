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
        System.out.println(session.getId());
        Admin admin=(Admin)session.getAttribute("admin");
        if(admin==null){
            response.sendError(404);
            return null;
        }
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
        Student student=studentService.getStudentById(id,admin.getGroup());
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
        Student student=studentService.getStudentById(id,admin.getGroup());
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
        Student student=studentService.getStudentById(id,admin.getGroup());
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
    public String changePassword(String password,HttpSession session){
        Admin admin=adminService.getAdminFromSession(session);
        if(admin!=null){
            admin.setPassword(password);
            adminService.changeAdminPassword(admin);
            return "true";
        }
        else{
            return "false";
        }
    }

    @RequestMapping("/changeInfo")
    @ResponseBody
    public String chagneInfo(String groupName,String groupSec,HttpSession session){
        Admin admin=adminService.getAdminFromSession(session);
        if(admin!=null){
            Group group=admin.getGroup();
            //group.setGroupName(groupName);
            group.setGroupSec(groupSec);
            groupService.updateGroup(group);
            return "true";
        }
        else{
            return "false";
        }
    }

    @RequestMapping("/initAllRequest")
    @ResponseBody
    public String initAllRequest(HttpSession session){
        Admin admin=adminService.getAdminFromSession(session);
        if(admin!=null){
            adminService.initStudentAllRequireState(admin);
            return "true";
        }
        else{
            return "false";
        }
    }

}
