package com.softwaresolution.ilearn;

public class quizData {
    String quizQuestion;
    String quizChoice_a;
    String quizChoice_b;
    String quizChoice_c;
    String quizChoice_d;
    String quizCorrectAnswer;

    public quizData(){

    }
    public quizData(String quizQuestion, String quizChoice_a, String quizChoice_b, String quizChoice_c, String quizChoice_d, String quizCorrectAnswer) {
        this.quizQuestion = quizQuestion;
        this.quizChoice_a = quizChoice_a;
        this.quizChoice_b = quizChoice_b;
        this.quizChoice_c = quizChoice_c;
        this.quizChoice_d = quizChoice_d;
        this.quizCorrectAnswer = quizCorrectAnswer;
    }

    public String getQuizQuestion() {
        return quizQuestion;
    }

    public void setQuizQuestion(String quizQuestion) {
        this.quizQuestion = quizQuestion;
    }

    public String getQuizChoice_a() {
        return quizChoice_a;
    }

    public void setQuizChoice_a(String quizChoice_a) {
        this.quizChoice_a = quizChoice_a;
    }

    public String getQuizChoice_b() {
        return quizChoice_b;
    }

    public void setQuizChoice_b(String quizChoice_b) {
        this.quizChoice_b = quizChoice_b;
    }

    public String getQuizChoice_c() {
        return quizChoice_c;
    }

    public void setQuizChoice_c(String quizChoice_c) {
        this.quizChoice_c = quizChoice_c;
    }

    public String getQuizChoice_d() {
        return quizChoice_d;
    }

    public void setQuizChoice_d(String quizChoice_d) {
        this.quizChoice_d = quizChoice_d;
    }

    public String getQuizCorrectAnswer() {
        return quizCorrectAnswer;
    }

    public void setQuizCorrectAnswer(String quizCorrectAnswer) {
        this.quizCorrectAnswer = quizCorrectAnswer;
    }
}
