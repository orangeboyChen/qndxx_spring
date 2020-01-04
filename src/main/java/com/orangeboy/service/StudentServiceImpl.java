package com.orangeboy.service;

import com.orangeboy.dao.StudentsDao;
import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentsDao studentsDao;
    @Autowired
    private GroupService groupService;
    @Override
    public void setStudentCompleteState(Student student, boolean state) {
        student.setCompleteState(state);
        student.setLastCompleteTime(System.currentTimeMillis());
        studentsDao.updateStudent(student);
    }

    @Override
    public void setStudentCompleted(Student student){
        Group group=groupService.queryGroupById(student.getGroupId());
        student.setRank(getCompletedCount(group)+1);
        setStudentCompleteState(student,true);
        student.setLastCompleteTime(System.currentTimeMillis());
        studentsDao.updateStudent(student);
    }

    @Override
    public int getCompletedCount(Group group){
        return studentsDao.queryCompletedStudents(group).size();
    }



    @Override
    public Student queryStudentById(String id, Group group) {
        return studentsDao.queryStudentById(id,group);
    }

    @Override
    public Student queryValidStudent(Student student, Group group) {
        return studentsDao.queryStudent(student);
    }



    @Override
    public List<Student> queryCompletedStudents(Group group) {
        return studentsDao.queryCompletedStudents(group);
    }

    @Override
    public List<Student> queryNotCompletedRequiredStudents(Group group) {
        return studentsDao.queryNotCompletedAndRequiredStudent(group);
    }

    @Override
    public Student queryStudentByName(String name, Group group) {
        return studentsDao.queryStudentByName(name,group);
    }

    @Override
    public void removeStudent(Student student) {
        studentsDao.removeStudent(student);
    }

    @Override
    public void insertStudent(Student student) {
        studentsDao.insertStudent(student);
    }

    @Override
    public int getStudentRank(Student student){
        return student.getRank();
    }





}
