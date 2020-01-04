package com.orangeboy.service;

import com.orangeboy.dao.AdminsDao;
import com.orangeboy.dao.StudentsDao;
import com.orangeboy.pojo.Admin;
import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private StudentsDao studentsDao;
    @Autowired
    private AdminsDao adminsDao;

    @Override
    public void setStudentRequireState(Student student, boolean state) {
        student.setRequireState(state);
        studentsDao.updateStudent(student);
    }

    @Override
    public void initStudentAllRequireState(Admin admin) {
        studentsDao.setAllRequired(admin.getGroup());
    }

    @Override
    public void initStudentAllCompleteState(Admin admin) {
        studentsDao.initTerm(admin.getGroup());
    }

    @Override
    public List<Student> queryRequiredStudents(Admin admin) {
        return studentsDao.queryRequiredStudents(admin.getGroup());
    }

    @Override
    public Admin getValidAdmin(Admin admin) {
        return adminsDao.queryAdmin(admin);
    }

    @Override
    public List<Student> getAllStudents(Admin admin) {
        return studentsDao.getStudentList(admin.getGroup());
    }

    @Override
    public List<Student> queryCompletedStudents(Admin admin) {
        List<Student> studentList = studentsDao.queryCompletedStudents(admin.getGroup());
        if(studentList.size()>0) return studentList;
        else return null;
    }

    @Override
    public boolean isAdminSession(HttpSession session) {
        Admin admin=(Admin)session.getAttribute("admin");
        return admin != null;
    }

    @Override
    public Admin getAdminFromSession(HttpSession session) {
        return (Admin)session.getAttribute("admin");
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminsDao.updateAdmin(admin);
    }

    @Override
    public void changeAdminPassword(Admin admin) {
        adminsDao.changeAdminPassword(admin);
    }

    @Override
    public void insertStudent(Student student, Admin admin) {
        student.setGroupId(admin.getGroupId());
        studentsDao.insertStudent(student);
    }

    @Override
    public Admin queryAdminByEmail(String email) {
        return adminsDao.queryAdminByEmail(email);
    }

    @Override
    public void insertAdmin(Admin admin) {
        adminsDao.insertAdmin(admin);
    }

    @Override
    public Admin queryAdminByRandom(String random) {
        return adminsDao.queryAdminByRandom(random);
    }

    @Override
    public void removeGroupStudents(Group group) {
        studentsDao.removeGroupStudents(group);
    }
}
