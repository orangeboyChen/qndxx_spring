package com.orangeboy.service;

import com.orangeboy.dao.GroupsDao;
import com.orangeboy.pojo.Group;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
    private GroupsDao groupsDao;
    public void setGroupsDao(GroupsDao groupsDao) {
        this.groupsDao = groupsDao;
    }

    @Override
    public void updateGroup(Group group) {
        groupsDao.updateGroup(group);
    }

    @Override
    public void addGroup(Group group) {
        groupsDao.addGroup(group);
    }

    @Override
    public Group queryGroupById(int id) {
        return groupsDao.queryGroupById(id);
    }

    @Override
    public Group queryGroupByName(String groupName) {
        return groupsDao.queryGroupByName(groupName);
    }

    @Override
    public Group queryGroupBySec(String groupSec) {
        return groupsDao.queryGroupBySec(groupSec);
    }
}
