package com.orangeboy.pojo;

import java.util.List;

public class AjaxMessage {
    private Group group;
    private List<Student> goodStudents;
    private List<Student> badStudents;

    public AjaxMessage(Group group,List<Student> goodStudents,List<Student> badStudents){
        this.group=group;
        this.goodStudents=goodStudents;
        this.badStudents=badStudents;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Student> getGoodStudents() {
        return goodStudents;
    }

    public void setGoodStudents(List<Student> goodStudents) {
        this.goodStudents = goodStudents;
    }

    public List<Student> getBadStudents() {
        return badStudents;
    }

    public void setBadStudents(List<Student> badStudents) {
        this.badStudents = badStudents;
    }

}
