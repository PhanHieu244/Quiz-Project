package com.Question;

public class Choice {
    public final Integer id;
    private String nameQuestion = null;// tên đáp án
    private String contentChoice;// nội dung đáp án
    private boolean key=false;// nếu choice là ans thì choice = true
    private char name;// dạng char vì name chỉ có 1 kí tự
    private String imageDataChoice = null;

    private float percentGrade; // sub 100

    public Choice(){
        id = null;
    }

    public Choice(Integer id, String content, String base64, float percent){
        this.id = id;
        contentChoice = content;
        imageDataChoice = base64;
        percentGrade = percent;
    }



    public Choice(String contentChoice, char name) {
        this.contentChoice = contentChoice;
        this.name = name;
        id = null;
    }

    public Choice(String content, String base64, float percent){
        this.id = null;
        contentChoice = content;
        imageDataChoice = base64;
        percentGrade = percent;
    }


    public String getContentChoice() {
        return contentChoice;
    }



    public char getName() {
        return name;
    }

    public boolean isKey() {
        return key;
    }

    public void setKey(boolean Key) {
        this.key = Key;
    }
    public void setPercentGrade(float percentGrade) {
        if(percentGrade<-100||percentGrade>100){
            System.out.println("error");
            return;
        }
        this.percentGrade = percentGrade;
    }
    public void setImageDataChoice(String imageDataChoice) {
        this.imageDataChoice = imageDataChoice;
    }

    public String getImageDataChoice() {
        return imageDataChoice;
    }



    public float getPercentGrade() {
        return percentGrade;
    }
}
