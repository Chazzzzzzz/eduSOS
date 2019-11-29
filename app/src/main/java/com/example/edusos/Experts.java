package com.example.edusos;

public class Experts {

    public String name;
    public String online;
    public String phone;
    public String questionAnswered;
    public String ratePerQuestion;
    public String subjects;

    public Experts(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQA() {
        return questionAnswered;
    }

    public void setQA(String questionAnswered) {
        this.questionAnswered = questionAnswered;
    }

    public String getRate() {
        return ratePerQuestion;
    }

    public void setRate(String ratePerQuestion) {
        this.ratePerQuestion = ratePerQuestion;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public Experts(String name, String online, String phone, String questionAnswered, String ratePerQuestion, String subjects){
        this.name = name;
        this.online = online;
        this.phone = phone;
        this.questionAnswered=questionAnswered;
        this.ratePerQuestion=ratePerQuestion;
        this.subjects=subjects;
    }



}