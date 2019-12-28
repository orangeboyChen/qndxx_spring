package com.orangeboy.service;

import com.orangeboy.dao.GroupDao;
import com.orangeboy.pojo.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
    private GroupDao groupDao;
    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public void updateGroup(Group group) {
        groupDao.updateGroup(group);
    }

    @Override
    public void addGroup(Group group) {
        groupDao.addGroup(group);
    }

    @Override
    public Group queryGroupById(int id) {
        return groupDao.queryGroupById(id);
    }

    @Override
    public Group queryGroupByName(String groupName) {
        return groupDao.queryGroupByName(groupName);
    }

    @Override
    public Group queryGroupBySec(String groupSec) {
        return groupDao.queryGroupBySec(groupSec);
    }
}
