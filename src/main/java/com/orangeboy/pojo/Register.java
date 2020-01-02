package com.orangeboy.pojo;

import com.orangeboy.util.mUtil;

public class Register {
    private int no;
    private int schoolId=-1;
    private String school=null;
    private String institution=null;


    private String groupName=null;
    private String groupSec=null;

    private String email=null;

    private String realName=null;
    private String password=null;

    private String code=null;

    private long requestTime;
    private String time;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public String getTime() {
        return mUtil.getTimeStr(requestTime);
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getGroupSec() {
        return groupSec;
    }

    public void setGroupSec(String groupSec) {
        this.groupSec = groupSec;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSchool(School school){
        this.school=school.getSchool();
        this.institution=school.getInstitution();
        this.schoolId=school.getSchoolId();
    }

    public void setGroup(Group group){
        this.groupName=group.getGroupName();
        this.groupSec=group.getGroupSec();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
