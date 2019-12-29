package com.orangeboy.service;

import com.orangeboy.dao.AdminDao;
import com.orangeboy.dao.StudentDao;
import com.orangeboy.pojo.Admin;
import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.Student;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private StudentDao studentDao;
    private AdminDao adminDao;

    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void setStudentRequireState(Student student, boolean state) {
        student.setRequireState(state);
        studentDao.updateStudent(student);
    }

    @Override
    public void initStudentAll(Admin admin) {
        List<Student> list=studentDao.getStudentList(admin.getGroup());
        for(Student student : list){
            student.setRequireState(true);
            student.setCompleteState(false);
            student.setRank(0);
            studentDao.updateStudent(student);
        }
    }

    @Override
    public void initStudentAllRequireState(Admin admin) {
        List<Student> list=studentDao.getStudentList(admin.getGroup());
        for(Student student : list){
            student.setRequireState(true);
            studentDao.updateStudent(student);
        }
    }

    @Override
    public void initStudentAllCompleteState(Admin admin) {
        List<Student> list=studentDao.getStudentList(admin.getGroup());
        for(Student student : list){
            student.setCompleteState(false);
            student.setRank(0);
            studentDao.updateStudent(student);
        }
    }

    @Override
    public List<Student> getRequiredStudents(Admin admin) {
        List<Student> students=studentDao.getStudentList(admin.getGroup());
        List<Student> requiredStudents=new ArrayList<Student>();
        for (Student student : students) {
            if (student.isRequireState()) {
                requiredStudents.add(student);
            }
        }
        return requiredStudents;
    }

    @Override
    public Admin getValidAdmin(Admin admin) {
        return adminDao.queryAdmin(admin);
    }

    @Override
    public List<Student> getAllStudents(Admin admin) {
        return studentDao.getStudentList(admin.getGroup());
    }

    @Override
    public List<Student> getCompletedStudents(Admin admin) {
        List<Student> students=studentDao.getStudentList(admin.getGroup());
        List<Student> completedStudents=new ArrayList<Student>();
        for (Student student : students) {
            if (student.isCompleteState()) {
                completedStudents.add(student);
            }
        }
        if(completedStudents.size()<=0) return null;
        else return completedStudents;
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
        adminDao.updateAdmin(admin);
    }

    @Override
    public void changeAdminPassword(Admin admin) {
        adminDao.changeAdminPassword(admin);
    }

    @Override
    public void addStudent(Student student, Admin admin) {
        student.setGroupId(admin.getGroupId());
        studentDao.addStudent(student);
    }

}
