package com.orangeboy.service;

import com.orangeboy.dao.SchoolsDao;
import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.School;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolsDao schoolsDao;
    @Override
    public School querySchoolByGroup(Group group) {
        return schoolsDao.querySchoolByGroup(group);
    }

    @Override
    public School querySchoolByName(String name) {
        return schoolsDao.querySchoolByName(name);
    }

    @Override
    public School querySchoolByData(String school, String institution) {
        return schoolsDao.querySchoolByData(school, institution);
    }

    @Override
    public void insertSchool(String school, String institution) {
        schoolsDao.insertSchool(school, institution);
    }
}
