package com.orangeboy.dao;

import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.Student;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper
public interface StudentsDao {
    @Insert("insert into Students(id, name, completeState, requireState, groupId,rank,completeCount) " +
            "values (#{id}, #{name}, 0 , 1, #{groupId},0,0)")
    void addStudent(Student student);

    @Delete("delete from Students " +
            "where id=#{id} and groupId=#{groupId}")
    void removeStudent(Student student);

    @Delete("delete from Students " +
            "where id=#{id} and groupId=#{group.groupId}")
    void removeStudentById(@Param("id")String id,@Param("group")Group group);

    @Update("update Students " +
            "set completeState=#{completeState},requireState=#{requireState},lastCompleteTime=#{lastCompleteTime},rank=#{rank} "+
            "where id=#{id} and groupId=#{groupId} ")
    void updateStudent(Student student);

    @Select("select * from Students " +
            "where id=#{id} and name=#{name} and groupId=#{groupId}")
    Student queryStudent(Student student);

    @Select(" select * from Students " +
            " where id=#{id} and groupId=#{group.groupId}")
    Student queryStudentById(@Param("id")String id, @Param("group")Group group);

    @Select("select * from Students " +
            "where name=#{name} and groupId=#{group.groupId}")
    Student queryStudentByName(@Param("name")String name,@Param("group")Group group);

    @Select("select * from Students and groupId=#{group.groupId}")
    List<Student> getAllStudentList();

    @Select("select * from Students where groupId=#{groupId}")
    List<Student> getStudentList(Group group);
}
