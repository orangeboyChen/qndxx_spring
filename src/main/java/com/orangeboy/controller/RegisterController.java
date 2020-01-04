package com.orangeboy.controller;

import com.orangeboy.pojo.Email;
import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.Register;
import com.orangeboy.pojo.School;
import com.orangeboy.service.EmailService;
import com.orangeboy.service.GroupService;
import com.orangeboy.service.RegisterService;
import com.orangeboy.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private EmailService emailService;


    //page list
    private final String RESPONSE_REGIST = "register";
    private final String RESPONSE_REGIST1 = "register/register1";
    private final String RESPONSE_REGIST2 = "register/register2";
    private final String RESPONSE_REGIST3 = "register/register3";
    private final String RESPONSE_REGIST4 = "register/register4";
    private final String RESPONSE_REGIST5 = "register/register5";

    //request list
    private final String REQUEST_REGIST = "";
    private final String REQUEST_REGIST1 = "/register1";
    private final String REQUEST_REGIST2 = "/register2";
    private final String REQUEST_REGIST3 = "/register3";
    private final String REQUEST_REGIST4 = "/register4";
    private final String REQUEST_REGIST5 = "/register5";

    //session object
    public static final String REGISTER="register";
    public static final String SCHOOL="registerSchool";
    private final String GROUP="registerGroup";


    @RequestMapping(REQUEST_REGIST)
    public String getRegisterPage(HttpSession session){
        Register register = (Register) session.getAttribute(REGISTER);
        if(register==null){
            register=new Register();
        }
        session.setAttribute(REGISTER,register);
        return RESPONSE_REGIST;
    }

    @RequestMapping(REQUEST_REGIST1)
    public String getRegister1Page(HttpSession session,Model model){
        Register register = registerService.getRegisterFromSession(session);
        model.addAttribute("school",register.getSchool());
        model.addAttribute("institution",register.getInstitution());

        //Progress

        model.addAttribute("progress",getProgress(register));
        return RESPONSE_REGIST1;
    }

    @RequestMapping(REQUEST_REGIST2)
    public String getRegister2Page(String school, String institution, HttpSession session,Model model){
        Register register = registerService.getRegisterFromSession(session);


        if(school!=null||institution!=null){
            if(school.length()>25||institution.length()>25){
                model.addAttribute("info","小伙子别皮哦");
                return "fail";
            }
            School registerSchool = null;
//        try {
//            registerSchool = (School) session.getAttribute(SCHOOL);
//        }catch (Exception e){}

//        if(registerSchool == null){
            registerSchool = schoolService.querySchoolByData(school,institution);
            if(registerSchool==null){
                registerSchool = new School(school,institution);
//            }
            }
            register.setSchool(registerSchool);
            session.setAttribute(SCHOOL,registerSchool);
        }


        model.addAttribute("groupName",register.getGroupName());
        model.addAttribute("groupSec",register.getGroupSec());


        model.addAttribute("progress",getProgress(register));
        return RESPONSE_REGIST2;
    }

    @RequestMapping(REQUEST_REGIST3)
    public String getRegister3Page(String groupName, String groupSec, HttpSession session, HttpServletResponse response, Model model) throws IOException {
        Register register = registerService.getRegisterFromSession(session);
        if(register.getSchool()==null) return RESPONSE_REGIST2;

        if(groupName!=null||groupSec!=null){
            if(groupName.length()>15||groupSec.length()>15){
                model.addAttribute("info","小伙子别逗我哦");
                return "fail";
            }
            School registerSchool = (School) session.getAttribute(SCHOOL);
            if(registerSchool.isOldSchool()) {
                Group sameGroup = groupService.queryGroupBySchoolAndName(groupName,registerSchool);
                if(sameGroup!=null){
                    response.getWriter().println(1);
                    return null;
                }
            }

            Group sameSecGroup=groupService.queryGroupBySec(groupSec);
            if(sameSecGroup!=null){
                response.getWriter().println(2);
                return null;
            }

            Group group=new Group(groupName, groupSec);
            register.setGroup(group);
            session.setAttribute(GROUP,group);
        }

        model.addAttribute("email",register.getEmail());
        model.addAttribute("progress",getProgress(register));
        return RESPONSE_REGIST3;
    }

    @RequestMapping(REQUEST_REGIST4)
    public String getRegister4Page(String email, String code, HttpSession session, HttpServletResponse response,Model model) throws IOException{
        Register register = registerService.getRegisterFromSession(session);

        if(register.getGroupName()==null) return RESPONSE_REGIST3;

        if(code.length()!=6){
            model.addAttribute("info","小朋友皮皮更健康哦");
            return "fail";
        }

        Email requestEmail = new Email(email, code);
        Email checkEmail;

        //Check the last request time
//        checkEmail = emailService.queryEmailByAddress(email);
//        if(System.currentTimeMillis() - checkEmail.getRequestTime() <= 300000){
//            response.getWriter().println("1");
//            return null;
//        }



        //1. Check if valid
        checkEmail = emailService.queryEmail(requestEmail);
        if(checkEmail==null){
            response.getWriter().println(2);
            return null;
        }

        //Valid
        register.setEmail(email);
        session.setAttribute(REGISTER, register);


        model.addAttribute("realName",register.getRealName());
        model.addAttribute("password",register.getPassword());
        model.addAttribute("progress",getProgress(register));
        return RESPONSE_REGIST4;
    }

    @RequestMapping(REQUEST_REGIST5)
    public String getRegister5Page(String realName, String password, HttpSession session, Model model){
        Register register = registerService.getRegisterFromSession(session);
        if(register.getEmail()==null) return RESPONSE_REGIST4;

        if(password.length()<8||password.length()>16){
            model.addAttribute("info","别皮哦小朋友");
            return "../fail";
        }

        register.setRealName(realName);
        register.setPassword(password);
        register.setRequestTime(System.currentTimeMillis());
        registerService.addRegister(register);
        emailService.removeEmail(register.getEmail());
        session.setAttribute(REGISTER,null);
        session.setAttribute(SCHOOL,null);
        return RESPONSE_REGIST5;
    }

    private String getProgress(Register register){
        if(register.getRealName()!=null) return "93";
        else if(register.getEmail()!=null) return "66";
        else if(register.getGroupSec()!=null) return "44";
        else if(register.getInstitution()!=null) return "22";
        else return "0";
    }
}
