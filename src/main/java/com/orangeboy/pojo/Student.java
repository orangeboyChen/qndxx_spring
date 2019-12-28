package com.orangeboy.pojo;

import com.orangeboy.util.mUtil;

public class Student {
    private String id;
    private String name;
    private boolean completeState=false;
    private boolean requireState=true;
    private int rank=1;
    private int groupName;
    private int school;
    private long lastCompleteTime;
    private int groupId;



    public String getTimeStr() {
        return mUtil.getTimeStr(lastCompleteTime);
    }


    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getGroupName() {
        return groupName;
    }

    public void setGroupName(int groupName) {
        this.groupName = groupName;
    }

    public int getSchool() {
        return school;
    }

    public void setSchool(int school) {
        this.school = school;
    }


    public Student(){}

    public Student(String name,int lastCompleteTime){
        this.name=name;
        this.lastCompleteTime=lastCompleteTime;
    }

    public long getLastCompleteTime() {
        return lastCompleteTime;
    }

    public void setLastCompleteTime(long lastCompleteTime) {
        this.lastCompleteTime = lastCompleteTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleteState() {
        return completeState;
    }

    public void setCompleteState(boolean completeState) {
        this.completeState = completeState;
    }

    public boolean isRequireState() {
        return requireState;
    }

    public void setRequireState(boolean requireState) {
        this.requireState = requireState;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

}
