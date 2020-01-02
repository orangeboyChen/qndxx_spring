package com.orangeboy.service;

import com.orangeboy.dao.EmailsDao;
import com.orangeboy.pojo.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private EmailsDao emailsDao;

    public void sendEmail(String to, String subject, String context){
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("register@nowcent.cn");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(context,true);
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Email queryEmailByAddress(String email) {
        return emailsDao.queryEmailByAddress(email);
    }

    @Override
    public Email queryEmail(Email email) {
        return emailsDao.queryEmail(email);
    }

    @Override
    public void insertEmail(Email email) {
        emailsDao.insertEmail(email);

    }

    @Override
    public void removeEmail(String email) {
        emailsDao.removeEmail(email);
    }

    @Override
    public void updateEmail(Email email) {
        emailsDao.updateEmail(email);
    }
}
