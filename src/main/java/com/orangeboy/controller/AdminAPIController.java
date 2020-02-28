package com.orangeboy.controller;

import com.orangeboy.pojo.Admin;
import com.orangeboy.pojo.Student;
import com.orangeboy.service.AdminService;
import com.orangeboy.service.GroupService;
import com.orangeboy.service.StudentService;
import static com.orangeboy.constant.SessionConstant.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

@Controller
public class AdminAPIController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AdminService adminService;

    public final static String PAGE1 = "admin/admin1";
    public final static String PAGE2 = "admin/admin2";
    public final static String PAGE3 = "admin/admin3";
    public final static String PAGE4 = "admin/admin4";
    public final static String PAGE5 = "admin/admin5";


    @RequestMapping("/admin/page/{page}")
    public String getAdminPage(@PathVariable("page")int page, HttpServletResponse response, HttpSession session, Model model) throws IOException {
        Admin admin = (Admin)session.getAttribute(ADMIN);
        if(admin==null){
            response.sendError(403);
            return null;
        }
        switch (page){
            case 1:
                Calendar calendar = Calendar.getInstance();

                //Model Attributes
                //greetingMsg
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                String greetingMsg = "";
                if(hour<=5) greetingMsg = "凌晨好！夜深了注意休息哦~";
                else if(hour <= 7) greetingMsg = "清晨好！新的一天也要开开心心呢~";
                else if(hour <= 11) greetingMsg = "上午好！";
                else if(hour <= 13) greetingMsg = "中午好！中午不睡下午崩溃哦~";
                else if(hour <= 16) greetingMsg = "下午好！";
                else if(hour <= 19) greetingMsg = "傍晚好！要不要看看落日呢~";
                else if(hour <= 22) greetingMsg = "晚上好！夜深了快洗洗睡吧~";
                else greetingMsg = "凌晨好！夜深了注意休息哦~";
                model.addAttribute("greetingMsg",greetingMsg);

                //completedCount
                int completedCount = 0;
                List<Student> goodStudentsList = adminService.queryCompletedStudents(admin);
                if(goodStudentsList!=null){
                    completedCount = goodStudentsList.size();
                }
                model.addAttribute("completedCount",completedCount);

                //startTime
                model.addAttribute("startTime",admin.getGroup().getTimeStr());

                //lastTime
                model.addAttribute("lastTime",session.getAttribute("adminLastTime"));
                return "admin/admin1";
            case 2:
                //Model Attributes
                //saying
                model.addAttribute("saying",admin.getGroup().getSaying());

                return "admin/admin2";
            case 3:
                //Model Attributes
                //goodStudents
                List<Student> goodStudents = adminService.queryCompletedStudents(admin);
                model.addAttribute("goodStudents",goodStudents);
                return "admin/admin3";
            case 4:
                //Model Attributes
                //requiredStudentCount
                List<Student> requiredList = adminService.queryRequiredStudents(admin);
                int requiredStudentCount = 0;
                if(requiredList!=null) requiredStudentCount = requiredList.size();
                model.addAttribute("requiredStudentCount",requiredStudentCount);

                //studentList
                List<Student> studentsList = adminService.getAllStudents(admin);
                model.addAttribute("studentsList",studentsList);
                return "admin/admin4";
            case 5:
                //Model Attributes
                //Group
                model.addAttribute("group",admin.getGroup());
                return "admin/admin5";
            default:
                response.sendError(403);
                return null;

        }
    }
}
