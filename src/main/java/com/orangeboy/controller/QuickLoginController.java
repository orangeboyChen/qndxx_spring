package com.orangeboy.controller;

import com.orangeboy.dao.AdminsDao;
import com.orangeboy.pojo.Admin;
import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.School;
import com.orangeboy.service.AdminService;
import com.orangeboy.service.GroupService;
import com.orangeboy.service.SchoolService;
import static com.orangeboy.constant.SessionConstant.*;
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
    @Autowired
    private GroupService groupService;
    @Autowired
    private SchoolService schoolService;
    @RequestMapping("/quickLogin/{random}")
    public String quickLogin(@PathVariable(name = "random") String random, HttpSession session, Model model){
        Admin admin = adminService.queryAdminByRandom(random);
        if(admin==null){
            model.addAttribute("info","别皮哦");
            return "fail";
        }
        else{

            admin.setRandom("");
            adminService.updateAdmin(admin);

            Group group = groupService.queryGroupById(admin.getGroupId());
            School school = schoolService.querySchoolByGroup(group);
            group.setSchoolObject(school);
            admin.setGroup(group);
            session.setAttribute(ADMIN,admin);
            session.setAttribute(GROUP,group);
            return "addMutiple";
        }

    }
}
