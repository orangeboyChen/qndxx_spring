package com.orangeboy.service;

import com.orangeboy.pojo.Group;

public interface GroupService {
    void updateGroup(Group group);
    void addGroup(Group group);
    Group queryGroupById(int id);
    Group queryGroupByName(String groupName);
    Group queryGroupBySec(String groupSec);

}
