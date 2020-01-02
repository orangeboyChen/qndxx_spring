package com.orangeboy.pojo;

import com.orangeboy.util.mUtil;

public class Group implements Cloneable{
    private int groupId;
    private String groupSec;
    private String groupName;
    private int schoolId;
    private String saying;
    private int adminId;
    private long startTime;
    private String timeStr;
    private String school;
    private String institution;
    private School schoolObject;

    public Group(){}

    public Group(String groupName, String groupSec){
        this.groupName = groupName;
        this.groupSec = groupSec;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public School getSchoolObject() {
        return schoolObject;
    }

    public void setSchoolObject(School schoolObject) {
        this.schoolObject = schoolObject;
        this.school=schoolObject.getSchool();
        this.institution=schoolObject.getInstitution();
    }

    @Override
    public Object clone() {
        Group group=null;
        try{
            group=(Group)super.clone();
            return group;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void setSchoolAndInstitution(String school,String institution){
        this.school=school;
        this.institution=institution;
    }

    public String getSchool() {
        return school;
    }

    public String getInstitution() {
        return institution;
    }

    public void setTimeStr(){
        timeStr = mUtil.getTimeStr(startTime);
    }

    public String getTimeStr(){
        return mUtil.getTimeStr(startTime);
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

    public String getSaying() {
        return saying;
    }

    public void setSaying(String saying) {
        this.saying = saying;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }


}
