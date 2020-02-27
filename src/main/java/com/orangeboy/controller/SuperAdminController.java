package com.orangeboy.controller;

import com.orangeboy.pojo.Admin;
import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.Register;
import com.orangeboy.pojo.School;
import com.orangeboy.service.*;
import com.orangeboy.constant.SessionConstant.*;
import com.orangeboy.util.mUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/superAdmin")
public class SuperAdminController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private AdminService adminService;

    @RequestMapping("")
    public String getPage(HttpSession session, HttpServletResponse response, Model model) throws IOException {
        String superAdmin = (String)session.getAttribute("superAdmin");
        if(superAdmin==null){
            response.sendError(404);
            return null;
        }

        List<Register> registerList = registerService.getAllRegisters();
        model.addAttribute("registerList",registerList);
        return "superAdmin";
    }

    @RequestMapping("/agree")
    @ResponseBody
    public String agree(String email, String school, String institution, String groupName){
        System.out.println(email);
        Register register = registerService.queryRegister(email);
        register.setSchool(school);
        register.setInstitution(institution);
        register.setGroupName(groupName);

        School mSchool = schoolService.querySchoolByData(school, institution);
        if(mSchool==null){
            mSchool = new School(school, institution);
            schoolService.insertSchool(school, institution);
            mSchool = schoolService.querySchoolByData(school,institution);
        }


        Group group = new Group(groupName, register.getGroupSec());
        group.setSchoolObject(mSchool);
        groupService.addGroup(group);
        group = groupService.queryGroupBySec(register.getGroupSec());

        String code = mUtil.getRandomStr(150);
        Admin admin = new Admin("admin",group.getGroupId(),register.getPassword(),register.getRealName(),register.getEmail(), code);
        adminService.insertAdmin(admin);
        admin = adminService.queryAdminByEmail(register.getEmail());
        group.setAdminId(admin.getId());
        group.setSchoolId(mSchool.getSchoolId());
        groupService.updateGroup(group);

        mUtil.agreeSuccess(register.getEmail(),code,register,emailService);
        registerService.deleteRegister(register);
        return null;
    }

    @RequestMapping("/reject")
    @ResponseBody
    public String reject(String email){
        Register register = registerService.queryRegister(email);
        mUtil.rejectSuccess(email,emailService);
        registerService.deleteRegister(register);
        return null;
    }
}
