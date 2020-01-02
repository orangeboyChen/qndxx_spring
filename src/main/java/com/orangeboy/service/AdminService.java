package com.orangeboy.service;

import com.orangeboy.pojo.Admin;
import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.Student;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface AdminService {
    void setStudentRequireState(Student student, boolean state);
    void initStudentAll(Admin admin);
    void initStudentAllRequireState(Admin admin);
    void initStudentAllCompleteState(Admin admin);
    List<Student> getRequiredStudents(Admin admin);
    Admin getValidAdmin(Admin admin);
    List<Student> getAllStudents(Admin admin);
    List<Student> getCompletedStudents(Admin admin);
    boolean isAdminSession(HttpSession session);
    Admin getAdminFromSession(HttpSession session);
    void updateAdmin(Admin admin);
    void changeAdminPassword(Admin admin);
    void addStudent(Student student,Admin admin);
    Admin queryAdminByEmail(String email);
    void insertAdmin(Admin admin);
    Admin queryAdminByRandom(String random);
}
