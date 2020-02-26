package com.orangeboy.controller;

import com.orangeboy.pojo.*;
import com.orangeboy.service.*;
import com.orangeboy.util.mUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import static com.orangeboy.controller.RegisterController.SCHOOL;

@RestController
@RequestMapping("/register/ajax")
public class RegisterAjaxController {
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private RegisterService registerService;

    String emailRegex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    @RequestMapping("/checkSchool")
    public String checkSchool(String school){
        School sameSchool = schoolService.querySchoolByName(school);
        if(sameSchool!=null){
            return "true";
        }
        else {
            return "false";
        }
    }

    @RequestMapping("/checkInstitution")
    public String checkInstitution(String school, String institution, HttpSession session){
        School sameSchool = schoolService.querySchoolByData(school,institution);
        if(sameSchool!=null){
            session.setAttribute(SCHOOL,sameSchool);
            return "true";
        }
        else {
            sameSchool = schoolService.querySchoolByName(school);
            if(sameSchool!=null){
                return "middle";
            }
            else {
                return "false";
            }
        }
    }

    @RequestMapping("/checkGroupName")
    public String checkGroupName(String groupName, HttpSession session){
        Object o = session.getAttribute(SCHOOL);
        School registerSchool = (School) session.getAttribute(SCHOOL);
        if(registerSchool.isOldSchool()) {
            Group sameGroup = groupService.queryGroupBySchoolAndName(groupName,registerSchool);
            if(sameGroup!=null){
                return "false";
            }
            else {
                return "true";
            }
        }
        else{
            return "true";
        }
    }

    @RequestMapping("/checkGroupSec")
    public String checkGroupSec(String groupSec, HttpSession session){
        Group group=groupService.queryGroupBySec(groupSec);
        if(group==null){
            return "true";
        }
        else{
            return "false";
        }
    }

    @RequestMapping("/getCode")
    public String getCode(String email, HttpSession session){


        //check email str has not been coded.

        //check if valid
        if(!email.matches(emailRegex)) return "INVALID_EMAIL";

        //check if email has been used
        Admin sameAdmin = adminService.queryAdminByEmail(email);
        if(sameAdmin!=null) return "EMAIL_HAS_BEEN_USED";

        //check if email has been registed
        Register sameRegister = registerService.queryRegister(email);
        if(sameRegister!=null) return "EMAIL_IS_CHECKING";

        //get code
        Email sameEmail = emailService.queryEmailByAddress(email);
        String code = mUtil.getRandomStr(6);
        System.out.println("code:"+code);
        Email newEmail;
        if(sameEmail!=null){
            if(System.currentTimeMillis() - sameEmail.getRequestTime() <= 300000){
                int lastingTime = (int)(300000 - System.currentTimeMillis() + sameEmail.getRequestTime())/60000;
                return String.valueOf(++lastingTime);
            }
            else{
                newEmail = new Email(email,code);
                emailService.updateEmail(newEmail);
                mUtil.sendCodeToEmail(email,code,emailService);
                return "true";
            }
        }
        else{
            newEmail = new Email(email,code);
            emailService.insertEmail(newEmail);
            mUtil.sendCodeToEmail(email,code,emailService);
            return "true";
        }
    }


}
