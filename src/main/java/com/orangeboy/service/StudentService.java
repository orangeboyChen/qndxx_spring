package com.orangeboy.service;

import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.Student;

import java.util.List;

public interface StudentService {
    void setStudentCompleteState(Student student, boolean state);
    void setStudentCompleted(Student student);
    Student queryStudentById(String id, Group group);
    Student queryValidStudent(Student student, Group group);
    int getCompletedCount(Group group);
    int getStudentRank(Student student);
    List<Student> getCompletedStudents(Group group);
    List<Student> getNotCompletedRequiredStudents(Group group);
    Student queryStudentByName(String name, Group group);
    void removeStudent(Student student);
    void addStudent(Student student);

}
