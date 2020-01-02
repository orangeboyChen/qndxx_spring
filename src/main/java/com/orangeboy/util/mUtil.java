package com.orangeboy.util;

import com.orangeboy.pojo.Register;
import com.orangeboy.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class mUtil {

    public static String getTimeStr(long l){
        Date date=new Date(l);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static String getRandomStr(int n){
        String string = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] c=new char[n];
        for(int i=0;i<n;i++){
            Random r=new Random();
            int index=r.nextInt(string.length());
            c[i]=string.charAt(index);
        }
        return String.valueOf(c);
    }


    public static void sendCodeToEmail(String to, String code,EmailService emailService) {
        emailService.sendEmail(to,"青年大学习在线提交验证码","<html>\n" +
                "    " +
                "<body>\n" +
                "        <h2>青年大学习在线提交验证码</h2>\n" +
                "        <p>您的验证码为<span style='size: 30px'>"  + code + "</span></p>\n" +
                "    " +
                "</body>\n" +
                "</html>");
    }

    public static void agreeSuccess(String to, String address, Register register, EmailService emailService){
        emailService.sendEmail(to, "审核通过！青年大学习在线提交系统",
                "<html>\n" +
                "    <body>\n" +
                        "        <h2>审核通过！您已完成青年大学习的注册。</h2>\n" +
                        "        <p>请核对您的信息</p>\n" +
                        "        <p>学校：" + register.getSchool() +"</p>\n" +
                        "        <p>学院：" + register.getInstitution() + "</p>\n" +
                        "        <p>班级：" + register.getGroupName() + "</p>\n" +
                        "        <p>班级代号：" + register.getGroupSec() + "</p>\n" +
                        "        <br><br>\n" +
                        "        <p>请<a href='http://qndxx.nowcent.cn/quickLogin/" + address + "'>点击这里</a>批量添加学生。感谢您对青年大学习在线提交系统的支持！</p>\n" +
                "        <br><br>\n" +
                "        <p>如果链接无法点击，请将以下链接复制到浏览器访问：</p>\n" +
                        "        <p>http://qndxx.nowcent.cn/quickLogin/" + address + "</p>\n" +
                "    " +
                "</body>\n" +
                "</html>");
    }

    public static void rejectSuccess(String to, EmailService emailService){
        emailService.sendEmail(to, "审核失败！青年大学习在线提交系统",
                "<html>\n" +
                        "    <body>\n" +
                        "        <h2>审核失败！有可能您提供了不正确的信息，或者您的班级已被注册。</h2>\n" +
                        "        <p>如果您确保信息正确，请重新注册。感谢您对青年大学习在线提交系统的支持！</p>\n" +
                        "        <br><br>\n" +
                        "    " +
                        "</body>\n" +
                        "</html>");
    }
}
