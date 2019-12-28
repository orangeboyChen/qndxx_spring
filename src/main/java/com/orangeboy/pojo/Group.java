package com.orangeboy.pojo;

import com.orangeboy.util.mUtil;

public class Group {
    private int groupId;
    private String groupSec;
    private String groupName;
    private String school;
    private String saying;
    private int adminId;
    private long startTime;
    private String timeStr;

    public void setTimeStr(){
        timeStr = mUtil.getTimeStr(startTime);
    }

    public String getTimeStr(){
        return timeStr;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setNewStartTime(){
        this.startTime=System.currentTimeMillis();
    }


    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }


    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupSec() {
        return groupSec;
    }

    public void setGroupSec(String groupSec) {
        this.groupSec = groupSec;
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

    public String getSaying() {
        return saying;
    }

    public void setSaying(String saying) {
        this.saying = saying;
    }
}
