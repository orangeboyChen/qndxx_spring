package com.orangeboy.controller;

import com.orangeboy.dao.AdminsDao;
import com.orangeboy.pojo.Admin;
import com.orangeboy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

@Controller
public class QuickLoginController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/quickLogin/{random}")
    public String quickLogin(@PathVariable(name = "random") String random, HttpSession session, Model model){
        Admin admin = adminService.queryAdminByRandom(random);
        if(admin==null){
            model.addAttribute("info","别皮哦");
            return "fail";
        }
        else{
            session.setAttribute("admin",admin);
            return "addMutiple";
        }

    }
}
