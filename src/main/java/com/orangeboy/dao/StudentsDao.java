package com.orangeboy.dao;

import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

public interface StudentsDao {
    void addStudent(Student student);
    void removeStudent(Student student);
    void removeStudentById(@Param("id")String id,@Param("group")Group group);
    void updateStudent(Student student);
    Student queryStudent(Student student);
    Student queryStudentById(@Param("id")String id, @Param("group")Group group);
    Student queryStudentByName(@Param("name")String name,@Param("group")Group group);

    List<Student> getAllStudentList();
    List<Student> getStudentList(Group group);
}
