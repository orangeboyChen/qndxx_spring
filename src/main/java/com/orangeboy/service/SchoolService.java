package com.orangeboy.service;

import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.School;
import org.apache.ibatis.annotations.Select;

public interface SchoolService {
    School querySchoolByGroup(Group group);
    School querySchoolByName(String name);
    School querySchoolByData(String school, String institution);
    void insertSchool(String school, String institution);
}
