package com.orangeboy.pojo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class School {
    private int schoolId;
    private String school;
    private String institution;

    public School(String school, String institution){
        this.school = school;
        this.institution = institution;
        this.schoolId = -1;
    }
    public boolean isOldSchool(){
        return schoolId > 0;
    }
}
