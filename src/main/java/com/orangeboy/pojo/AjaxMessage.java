package com.orangeboy.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AjaxMessage {
    private Group group;
    private List<Student> goodStudents;
    private List<Student> badStudents;

    public AjaxMessage(Group group,List<Student> goodStudents,List<Student> badStudents){
        this.group=group;
        this.goodStudents=goodStudents;
        this.badStudents=badStudents;
    }


}
