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

import static com.orangeboy.constant.SessionConstant.*;

@Controller
public class UploadController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/uploadPic")
    @ResponseBody
    public String upload(@RequestParam("file") CommonsMultipartFile file, Model model,HttpSession session) {
        String[] acceptTypes = {"image/jpeg","image/png","image/gif"};
        String fileType = file.getContentType();
        boolean flag = false;
        for (String type:acceptTypes) {
            if(type.equals(fileType)){
                flag = true;
                break;
            }
        }

        if(!flag){
            model.addAttribute("msg","别皮了哦");
            session.setAttribute(FILE_PROCESS,null);
            return FileConstant.NOT_A_SCREENSHOT;
        }

        Student student = (Student)session.getAttribute(STUDENT);
        if(student==null){
            return FileConstant.SUCCESS;
        }

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
        student.setCompleteState(true);
        session.setAttribute(STUDENT, student);
        return FileConstant.SUCCESS;
    }

    @RequestMapping("/success")
    public String success(HttpSession session, Model model, HttpServletResponse response) throws IOException {
        Student student = (Student) session.getAttribute(STUDENT);
        Student hasDoneStudent = (Student) session.getAttribute(HAS_DONE_STUDENT);
        if(student==null&&hasDoneStudent==null){
            response.sendError(403);
            return null;
        }

        if(student==null){
            model.addAttribute("title","你已经提交过了");
            model.addAttribute("rank",hasDoneStudent.getRank());
        }
        else {
            if(!student.isCompleteState()){
                response.sendError(403);
                return null;
            }
            model.addAttribute("title", "提交成功");
            model.addAttribute("rank", student.getRank());
            session.setAttribute(HAS_DONE_STUDENT, student);
            session.setAttribute(STUDENT, null);
        }
        return "success";
    }

    //API
    @PostMapping("/uploadProcess")
    @ResponseBody
    public String getUploadProcess(HttpSession session){
        if(session.getAttribute(FILE_PROCESS)==null) return "0";
        return session.getAttribute(FILE_PROCESS).toString();
    }

}
