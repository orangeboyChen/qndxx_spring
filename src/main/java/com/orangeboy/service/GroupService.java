package com.orangeboy.service;

import com.orangeboy.pojo.Group;
import com.orangeboy.pojo.School;

public interface GroupService {
    void updateGroup(Group group);
    void addGroup(Group group);
    Group queryGroupById(int id);
    Group queryGroupByName(String groupName);
    Group queryGroupBySec(String groupSec);
    Group queryGroupBySchoolAndName(String groupName, School school);

}
