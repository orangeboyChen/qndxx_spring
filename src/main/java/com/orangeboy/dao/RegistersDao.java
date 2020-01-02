package com.orangeboy.dao;

import com.orangeboy.pojo.Register;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RegistersDao {
    @Insert("insert into Registers(schoolId,school,institution,groupName,groupSec,email,realName,password,requestTime) " +
            "values (#{schoolId},#{school},#{institution},#{groupName},#{groupSec},#{email},#{realName},#{password},#{requestTime})")
    void addRegister(Register register);

    @Delete("delete from Registers where no=#{no}")
    void deleteRegister(Register register);

    @Update("update Registers " +
            "set schoolId=#{schoolId},school=#{school},institution=#{institution},groupName=#{groupName},groupSec=#{groupSec},email=#{email},realName=#{realName},password=#{password},requestTime=#{requestTime} " +
            "where no=#{no}")
    void updateRegister(Register register);

    @Select("select * from Registers where email=#{email}")
    Register queryRegister(String email);

    @Select("select * from Registers")
    List<Register> getAllRegisters();
}
