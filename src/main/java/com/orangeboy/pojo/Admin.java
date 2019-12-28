package com.orangeboy.pojo;

import com.orangeboy.util.mUtil;

public class Admin {

    private int id;
    private int groupId;
    private String groupName;
    private String school;
    private String name;
    private String password;
    private Group group;
    private long lastLoginTime;

    public long getLastLoginTime() {
        return lastLoginTime;
    }
    public String getTimeStr(){
        return mUtil.getTimeStr(lastLoginTime);
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public void setNewLoginTime(){
        this.lastLoginTime=System.currentTimeMillis();
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public Admin(){}
    public Admin(int groupId,String name,String password){
        this.groupId=groupId;
        this.name=name;
        this.password=password;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
