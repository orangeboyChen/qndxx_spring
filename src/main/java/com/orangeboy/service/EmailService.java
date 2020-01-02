package com.orangeboy.service;

import com.orangeboy.pojo.Email;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface EmailService {
    void sendEmail(String to, String subject, String context);
    Email queryEmailByAddress(String email);
    Email queryEmail(Email email);
    void insertEmail(Email email);
    void removeEmail(String email);
    void updateEmail(Email email);

}
