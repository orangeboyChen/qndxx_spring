package com.orangeboy.pojo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
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
    private String picName;
    private String groupSec;


    public Student(String id, String name, Group group){
        this.id=id;
        this.name=name;
        this.groupId=group.getGroupId();
    }

    public Student(String name,int lastCompleteTime){
        this.name=name;
        this.lastCompleteTime=lastCompleteTime;
    }

}
