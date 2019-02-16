package com.softwaresolution.ilearn;

public class LoginTeacher {
    public String teacherPassword;
    public String teacherName;

    public LoginTeacher(){

    }

    public LoginTeacher(String teacherName, String teacherPassword) {
        this.teacherPassword = teacherPassword;
        this.teacherName = teacherName;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
