package com.Question;

public class Choice {
    private int id;
    private String contentChoice;// nội dung đáp án
    private double point;// điểm có được khi chọn đáp án
    private boolean key;// nếu choice là ans thì choice = true
    private char name;// dạng char vì name chỉ có 1 kí tự
    private String imageDataChoice = null;

    private float percentGrade; // sub 100

    public Choice(){}

    public Choice(String contentChoice, char name) {
        this.contentChoice = contentChoice;
        this.name = name;
    }

    public Choice(int id ,String content, String base64, float percent){
        this.id = id;
        contentChoice = content;
        imageDataChoice = base64;
        percentGrade = percent;
    }

    public Choice(String content, String base64, float percent){
        contentChoice = content;
        imageDataChoice = base64;
        percentGrade = percent;
    }

    public String getContentChoice() {
        return contentChoice;
    }

    public double getPoint() {
        return point;
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

    public void setImageDataChoice(String imageDataChoice) {
        this.imageDataChoice = imageDataChoice;
    }

    public String getImageDataChoice() {
        return imageDataChoice;
    }

    public boolean setPoint(double point) {
        if(point > 1 || point < -1) return false;
        this.point = point;
        return true;
    }

    public float getPercentGrade() {
        return percentGrade;
    }
}
