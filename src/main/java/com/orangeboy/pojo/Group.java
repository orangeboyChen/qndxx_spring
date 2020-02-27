package com.orangeboy.pojo;

import com.orangeboy.util.mUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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

    public Group(String groupName, String groupSec){
        this.groupName = groupName;
        this.groupSec = groupSec;
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


    public void setTimeStr(){
        timeStr = mUtil.getTimeStr(startTime);
    }

    public String getTimeStr(){
        return mUtil.getTimeStr(startTime);
    }

    public void setNewStartTime(){
        this.startTime=System.currentTimeMillis();
    }



}
