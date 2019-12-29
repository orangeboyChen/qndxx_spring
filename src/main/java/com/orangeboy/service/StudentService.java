package com.orangeboy.service;

import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.Student;

import java.util.List;

public interface StudentService {
    void setStudentCompleteState(Student student, boolean state);
    void setStudentCompleted(Student student);
    Student getStudentById(String id,Group group);
    Student getValidStudent(Student student,Group group);
    int getCompletedCount(Group group);
    int getStudentRank(Student student);
    List<Student> getCompletedStudents(Group group);
    List<Student> getNotCompletedRequiredStudents(Group group);
    Student getStudentByName(String name,Group group);
    void removeStudent(Student student);
}
