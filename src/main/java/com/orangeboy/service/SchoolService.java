package com.orangeboy.service;

import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.School;
import org.springframework.stereotype.Service;

public interface SchoolService {
    School querySchoolByGroup(Group group);
}
