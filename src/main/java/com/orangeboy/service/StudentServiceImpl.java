package com.orangeboy.service;

import com.orangeboy.dao.StudentDao;
import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;
    @Autowired
    private GroupService groupService;
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
    @Override
    public void setStudentCompleteState(Student student, boolean state) {
        student.setCompleteState(state);
        student.setLastCompleteTime(System.currentTimeMillis());
        studentDao.updateStudent(student);
    }

    @Override
    public void setStudentCompleted(Student student){
        Group group=groupService.queryGroupById(student.getGroupId());
        student.setRank(getCompletedCount(group)+1);
        setStudentCompleteState(student,true);
        student.setLastCompleteTime(System.currentTimeMillis());
        studentDao.updateStudent(student);
    }

    @Override
    public int getCompletedCount(Group group){
        List<Student> students=studentDao.getStudentList(group);
        int count=0;
        for(Student student : students){
            if(student.isCompleteState()) count++;
        }
        return count;
    }



    @Override
    public Student getStudentById(String id,Group group) {
        return studentDao.queryStudentById(id,group);
    }

    @Override
    public Student getValidStudent(Student student,Group group) {
        return studentDao.queryStudent(student);
    }



    @Override
    public List<Student> getCompletedStudents(Group group) {
        List<Student> students=studentDao.getStudentList(group);
        List<Student> completedStudents=new ArrayList<Student>();
        for (Student student : students) {
            if (student.isCompleteState()) {
                completedStudents.add(student);
            }
        }
        return completedStudents;
    }

    @Override
    public List<Student> getNotCompletedRequiredStudents(Group group) {
        List<Student> students=studentDao.getStudentList(group);
        List<Student> qStudents=new ArrayList<Student>();
        for (Student student : students) {
            if ((!student.isCompleteState())&&student.isRequireState()) {
                qStudents.add(student);
            }
        }
        return qStudents;
    }

    @Override
    public Student getStudentByName(String name, Group group) {
        return studentDao.queryStudentByName(name,group);
    }

    @Override
    public void removeStudent(Student student) {
        studentDao.removeStudent(student);
    }

    @Override
    public int getStudentRank(Student student){
        return student.getRank();
    }





}
