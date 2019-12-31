package com.orangeboy.dao;

import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.School;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SchoolsDao {
    @Select("select * from Schools where schoolId=#{schoolId}")
    School querySchoolByGroup(Group group);
}
