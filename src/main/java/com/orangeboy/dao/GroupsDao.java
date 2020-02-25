package com.orangeboy.dao;

import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.School;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GroupsDao {
    @Select("select * from Groups " +
            "where groupId=#{id}")
    Group queryGroupById(int id);

    @Select("select * from Groups " +
            "where groupName=#{groupName}")
    Group queryGroupByName(String groupName);

    @Select("select * from Groups where groupName=#{groupName} and schoolId=#{school.schoolId}")
    Group queryGroupBySchoolAndName(@Param("groupName")String groupName, @Param("school")School school);

    @Select("select * from Groups " +
            "where groupSec=#{groupSec}")
    Group queryGroupBySec(String groupSec);

    @Insert("insert into Groups(groupSec, groupName, schoolId, adminId, saying) " +
            "values (#{groupSec}, #{groupName}, #{schoolId}, #{adminId}, #{saying})")
    void addGroup(Group group);

    @Delete("delete from Groups " +
            "where groupId=#{groupId}")
    void removeGroup(Group group);

    @Update("update Groups " +
            "set groupSec=#{groupSec}, groupName=#{groupName}, schoolId=#{schoolId}, saying=#{saying},adminId=#{adminId},startTime=#{startTime} " +
            "where groupId=#{groupId} ")
    void updateGroup(Group group);


}
