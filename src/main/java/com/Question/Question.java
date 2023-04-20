package com.Question;

import java.util.ArrayList;
import java.util.List;
public class Question {
    private int idQuestion;// id của câu hỏi
    private String contentQuestion;// nội dung đề bài
    private List<Choice> choices = new ArrayList<Choice>();// danh sách câu trả lời
    private String key;// dạng chuỗi vì nó có thể chứa nhiều đáp án
    private String imageDataQs = null;
    public Question(){
    }
    public Question(String s){
    }

    public Question(String contentQuestion, List<Choice> choices, String key) {
        this.contentQuestion = contentQuestion;
        this.choices = choices;
        this.key = key;
    }

    public Question(String contentQuestion, String base64, List<Choice> choices) {
        this.contentQuestion = contentQuestion;
        this.choices = choices;
        imageDataQs = base64;
    }

    public Question(String contentQuestion, int idQuestion){
        this.contentQuestion = contentQuestion;
        this.idQuestion = idQuestion;
    }

    public void setContentQuestion(String contentQuestion) {
        this.contentQuestion = contentQuestion;
    }// đặt tiêu đề câu hỏi

    public void setKey(String key) {
        this.key = key;
    }

    public void setImageDataQs(String imageDataQs) {
        this.imageDataQs = imageDataQs;
    }
    public void setImageDataLastChoice(String imageDataLastChoice){
        choices.get(choices.size()-1).setImageDataChoice(imageDataLastChoice);
    }
    public int getIdQuestion() {
        return idQuestion;
    }

    public String getContentQuestion() {
        return contentQuestion;
    }

    public String getImageDataQs() {
        return imageDataQs;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public String getKey() {
        return key;
    }
    // thêm đáp án vào list ans, không có điểm
    public void addChoice(String s, char name){
        Choice c= new Choice(s,name);
        choices.add(c);
    }

    // thêm đáp án key[0] thành đáp án đúng
    public boolean setKeyChoice(){
        for (Choice choice : choices) {
            if (choice.getName() == key.charAt(0)) {
                choice.setKey(true);
                return true;
            }
        }
        return false;
    }

    // thêm đáp án có tên là c thành đáp án đúng
    public boolean setKeyChoice(char c){
        for (Choice choice : choices) {
            if (choice.getName() == c) {
                choice.setKey(true);
                return true;
            }
        }
        return false;
    }
    // show ra question
    public void show(){
        System.out.println(idQuestion);
        System.out.println(contentQuestion);
        System.out.println(imageDataQs);
        for (Choice choice : choices) {
            System.out.println(choice.getName() + " " + choice.getContentChoice() + " " + choice.isKey() + " " + choice.getImageDataChoice());
        }

        System.out.println(key);
    }
}