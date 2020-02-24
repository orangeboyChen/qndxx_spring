package com.orangeboy.controller;

import com.orangeboy.constant.FileConstant;
import com.orangeboy.pojo.Student;
import com.orangeboy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UploadController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/uploadPic")
    @ResponseBody
    public String upload(@RequestParam("file") CommonsMultipartFile file, Model model,HttpSession session) {
        String[] acceptTypes = {"image/jpeg","image/png","image/gif"};
        String fileType = file.getContentType();
        System.out.println(fileType);
        boolean flag = false;
        for (String type:acceptTypes) {
            if(type.equals(fileType)){
                flag = true;
                break;
            }
        }

        if(!flag){
            model.addAttribute("msg","别皮了哦");
            session.setAttribute("fileProcess",null);
            return FileConstant.NOT_A_SCREENSHOT;
        }

        Student student = (Student)session.getAttribute("student");

        String path = FileConstant.PATH;
        File realPath = new File(path);
        String fileName = UUID.randomUUID().toString() + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')+1);
        if(!realPath.exists()) realPath.mkdir();
        try {
            file.transferTo(new File(realPath + "/" + fileName));
        }catch (Exception e){
            System.out.println(e);
            return FileConstant.UNEXPECTED_ERROR;
        }
        studentService.setStudentCompleted(student,fileName);
        session.setAttribute("student", student);
        session.setAttribute("rank", studentService.getStudentRank(student));
        return FileConstant.SUCCESS;
    }

    @RequestMapping("/success")
    public String success(HttpSession session, Model model, HttpServletResponse response) throws IOException {
        Object o = session.getAttribute("rank");
        if(o==null||"".equals(o.toString().trim())){
            response.sendError(500);
            return null;
        }
        model.addAttribute("rank",session.getAttribute("rank"));
        session.setAttribute("hasDoneStudent",session.getAttribute("student"));
        session.setAttribute("student",null);
        return "success";
    }

    @PostMapping("/uploadProcess")
    @ResponseBody
    public String getUploadProcess(HttpSession session){
        if(session.getAttribute("fileProcess")==null) return "0";
        return session.getAttribute("fileProcess").toString();
    }

}