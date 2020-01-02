package com.orangeboy.pojo;

public class School {
    private int schoolId;
    private String school;
    private String institution;

    public School(){}
    public School(String school, String institution){
        this.school = school;
        this.institution = institution;
        this.schoolId = -1;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
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

    public boolean isOldSchool(){
        return schoolId > 0;
    }
}
