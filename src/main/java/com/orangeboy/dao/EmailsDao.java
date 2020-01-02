package com.orangeboy.dao;

import com.orangeboy.pojo.Email;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmailsDao {
    @Select("select * from Emails where email=#{email}")
    Email queryEmailByAddress(String email);

    @Select("select * from Emails where email=#{email} and code=#{code}")
    Email queryEmail(Email email);

    @Insert("insert into Emails(email,code,requestTime) values (#{email},#{code},#{requestTime})")
    void insertEmail(Email email);

    @Delete("delete from Emails where email=#{email}")
    void removeEmail(String email);

    @Update("update Emails set code=#{code}, requestTime=#{requestTime} where email=#{email}")
    void updateEmail(Email email);
}
