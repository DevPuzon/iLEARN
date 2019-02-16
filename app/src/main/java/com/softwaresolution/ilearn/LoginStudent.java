package com.softwaresolution.ilearn;

public class LoginStudent {
    String studentName;
    String studentPassword;
    String studentTeacherAssigned;

    public LoginStudent(){

    }

    public LoginStudent(String studentName, String studentPassword, String studentTeacherAssigned) {
        this.studentName = studentName;
        this.studentPassword = studentPassword;
        this.studentTeacherAssigned = studentTeacherAssigned;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getStudentTeacherAssigned() {
        return studentTeacherAssigned;
    }

    public void setStudentTeacherAssigned(String studentTeacherAssigned) {
        this.studentTeacherAssigned = studentTeacherAssigned;
    }
}
