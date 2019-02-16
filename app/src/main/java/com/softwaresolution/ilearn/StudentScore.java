package com.softwaresolution.ilearn;

public class StudentScore {
    String studentScore;
    String activityNo;
    public StudentScore(){

    }

    public StudentScore(String studentScore, String activityNo) {
        this.studentScore = studentScore;
        this.activityNo = activityNo;
    }

    public String getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(String studentScore) {
        this.studentScore = studentScore;
    }

    public String getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(String activityNo) {
        this.activityNo = activityNo;
    }
}
