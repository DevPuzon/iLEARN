package com.softwaresolution.ilearn;

public class studentData {
    public  String studentName;
    public String studentPassword;

    public studentData(String studentUsername, String studentPassword) {
        this.studentName = studentUsername;
        this.studentPassword = studentPassword;
    }

    public String getStudentUsername() {
        return studentName;
    }

    public void setStudentUsername(String studentUsername) {
        this.studentName = studentUsername;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }
}
