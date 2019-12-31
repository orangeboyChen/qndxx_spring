package com.orangeboy.controller;

import com.orangeboy.pojo.Admin;
import com.orangeboy.pojo.AjaxMessage;
import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.Student;
import com.orangeboy.service.AdminService;
import com.orangeboy.service.GroupService;
import com.orangeboy.service.SchoolService;
import com.orangeboy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AjaxController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private SchoolService schoolService;
    @RequestMapping("/ajax/checkGroupSec")
    @ResponseBody
    public Group checkGroupSec(String groupSec, HttpSession session){
        Group group=groupService.queryGroupBySec(groupSec);
        group.setSchoolObject(schoolService.querySchoolByGroup(group));
        if(group!=null){
            session.setAttribute("group",group);
            group.setTimeStr();
        }
        else{
            session.setAttribute("group",null);
        }
        Group groupCloned=(Group) group.clone();
        groupCloned.setAdminId(-1);
        return groupCloned;
    }
}
