package com.orangeboy.service;

import com.orangeboy.dao.AdminsDao;
import com.orangeboy.dao.StudentsDao;
import com.orangeboy.pojo.Admin;
import com.orangeboy.pojo.Student;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private StudentsDao studentsDao;
    private AdminsDao adminsDao;

    public void setAdminsDao(AdminsDao adminsDao) {
        this.adminsDao = adminsDao;
    }
    public void setStudentsDao(StudentsDao studentsDao) {
        this.studentsDao = studentsDao;
    }

    @Override
    public void setStudentRequireState(Student student, boolean state) {
        student.setRequireState(state);
        studentsDao.updateStudent(student);
    }

    @Override
    public void initStudentAll(Admin admin) {
        List<Student> list= studentsDao.getStudentList(admin.getGroup());
        for(Student student : list){
            student.setRequireState(true);
            student.setCompleteState(false);
            student.setRank(0);
            studentsDao.updateStudent(student);
        }
    }

    @Override
    public void initStudentAllRequireState(Admin admin) {
        List<Student> list= studentsDao.getStudentList(admin.getGroup());
        for(Student student : list){
            student.setRequireState(true);
            studentsDao.updateStudent(student);
        }
    }

    @Override
    public void initStudentAllCompleteState(Admin admin) {
        List<Student> list= studentsDao.getStudentList(admin.getGroup());
        for(Student student : list){
            student.setCompleteState(false);
            student.setRank(0);
            studentsDao.updateStudent(student);
        }
    }

    @Override
    public List<Student> getRequiredStudents(Admin admin) {
        List<Student> students= studentsDao.getStudentList(admin.getGroup());
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
        return adminsDao.queryAdmin(admin);
    }

    @Override
    public List<Student> getAllStudents(Admin admin) {
        return studentsDao.getStudentList(admin.getGroup());
    }

    @Override
    public List<Student> getCompletedStudents(Admin admin) {
        List<Student> students= studentsDao.getStudentList(admin.getGroup());
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
        adminsDao.updateAdmin(admin);
    }

    @Override
    public void changeAdminPassword(Admin admin) {
        adminsDao.changeAdminPassword(admin);
    }

    @Override
    public void addStudent(Student student, Admin admin) {
        student.setGroupId(admin.getGroupId());
        studentsDao.addStudent(student);
    }

}
