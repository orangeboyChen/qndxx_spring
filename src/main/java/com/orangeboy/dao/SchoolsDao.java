package com.orangeboy.dao;

import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.School;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SchoolsDao {
    @Select("select * from Schools where schoolId=#{schoolId}")
    School querySchoolByGroup(Group group);

    @Select("select * from Schools where school=#{name}")
    School querySchoolByName(String name);

    @Select("select * from Schools where school=#{school} and institution=#{institution}")
    School querySchoolByData(@Param("school")String school, @Param("institution")String institution);

    @Insert("insert into Schools(school,institution) values (#{school},#{institution})")
    void insertSchool(@Param("school")String school, @Param("institution")String institution);


}
