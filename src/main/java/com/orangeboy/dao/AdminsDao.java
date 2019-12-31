package com.orangeboy.dao;

import com.orangeboy.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
public interface AdminsDao {
    @Select("select * from Admins " +
            "where name=#{name} and password=#{password}")
    Admin queryAdmin(Admin admin);

    @Update("update Admins " +
            "set lastLoginTime=#{lastLoginTime} " +
            "where id=#{id}")
    void updateAdmin(Admin admin);

    @Update("update Admins " +
            "set password=#{password} " +
            "where id=#{id}")
    void changeAdminPassword(Admin admin);

}
