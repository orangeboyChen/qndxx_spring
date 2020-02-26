package com.orangeboy.pojo;

import com.orangeboy.util.mUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Admin {

    private int id;
    private int groupId;
    private String groupName;
    private String school;
    private String name;
    private String password;
    private Group group;
    private long lastLoginTime;
    private String realName;
    private String email;
    private String random;

    public String getTimeStr(){
        return mUtil.getTimeStr(lastLoginTime);
    }

    public void setNewLoginTime(){
        this.lastLoginTime=System.currentTimeMillis();
    }

    public Admin(int groupId,String name,String password){
        this.groupId=groupId;
        this.name=name;
        this.password=password;
    }

    public Admin(String name, int groupId, String password, String realName, String email, String random){
        this.name = name;
        this.groupId = groupId;
        this.password = password;
        this.realName = realName;
        this.email = email;
        this.random = random;
    }

}
