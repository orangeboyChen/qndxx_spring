package com.orangeboy.dao;

import com.orangeboy.pojo.Admin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
public interface AdminsDao {
    @Select("select * from Admins " +
            "where name=#{name} and password=#{password}")
    Admin queryAdmin(Admin admin);

    @Update("update Admins " +
            "set lastLoginTime=#{lastLoginTime},random=#{random} " +
            "where id=#{id}")
    void updateAdmin(Admin admin);

    @Update("update Admins " +
            "set password=#{password} " +
            "where id=#{id}")
    void changeAdminPassword(Admin admin);

    @Select("select * from Admins where email=#{email}")
    Admin queryAdminByEmail(String email);

    @Select("select * from Admins where random=#{random}")
    Admin queryAdminByRandom(String random);

    @Insert("insert into Admins" +
            "(name,groupId,password,realName,email,random) " +
            "values " +
            "(#{name},#{groupId},#{password},#{realName},#{email},#{random})")
//    void insertAdmin(@Param("name")String name, @Param("groupId") int groupId, @Param("password") String password, @Param("realName") String realName, @Param("email") String email, @Param("random") String random);
    void insertAdmin(Admin admin);
}
