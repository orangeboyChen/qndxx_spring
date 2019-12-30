package com.orangeboy.dao;

import com.orangeboy.pojo.Group;

public interface GroupsDao {
    Group queryGroupById(int id);
    Group queryGroupByName(String groupName);
    Group queryGroupBySec(String groupSec);
    void addGroup(Group group);
    void removeGroup(Group group);
    void updateGroup(Group group);
}
