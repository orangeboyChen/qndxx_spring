package com.orangeboy.pojo;

import com.orangeboy.util.mUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
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


    public String getTime() {
        return mUtil.getTimeStr(requestTime);
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

    public void setSchool(String school){
        this.school = school;
    }

}
