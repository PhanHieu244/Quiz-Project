package com.Question;

import java.util.ArrayList;
import java.util.List;
public class Question {
    public final Integer idQuestion;// id của câu hỏi
    private String nameQuestion = "";// tên câ hỏi
    private String contentQuestion;// nội dung đề bài
    private List<Choice> choices = new ArrayList<>();// danh sách câu trả lời
    private String key;// dạng chuỗi vì nó có thể chứa nhiều đáp án
    private String imageDataQs = null;
    public Question(){
        this.idQuestion = null;
    }

    public Question(String s){
        contentQuestion = s;
        idQuestion = null;
    }

    public Question(String nameQuestion,String contentQuestion, List<Choice> choices, String key) {
        this.contentQuestion = contentQuestion;
        this.nameQuestion = nameQuestion;
        this.choices = choices;
        this.key = key;
        idQuestion = null;
    }

    public Question(String nameQuestion, String contentQuestion, String base64, List<Choice> choices) {
        this.contentQuestion = contentQuestion;
        this.nameQuestion = nameQuestion;
        this.choices = choices;
        imageDataQs = base64;
        idQuestion = null;
    }

    public Question(String nameQuestion, String contentQuestion, String base64, List<Choice> choices, Integer idQuestion) {
        this.contentQuestion = contentQuestion;
        this.nameQuestion = nameQuestion;
        this.choices = choices;
        imageDataQs = base64;
        this.idQuestion = idQuestion;
    }

    public Question(String nameQuestion, String contentQuestion, Integer idQuestion){
        this.contentQuestion = contentQuestion;
        this.idQuestion = idQuestion;
        this.nameQuestion = nameQuestion;
    }

    public boolean isMultiChoice(){
        int count = 0;
        for (Choice choice:
             choices) {
            if (choice.getPercentGrade() > 0){
                count++;
                if (count > 1) return true;
            }
        }
        return false;
    }

    public void addChoice(String s, char name){
        Choice c= new Choice(s,name);
        choices.add(c);
    }
    public void setNameAndContentQs(String s) {
        int id = s.indexOf(":");
        String nameQs = (id > -1) ? s.substring(0, id) : "";
        String contentQs = s.substring(id + ((s.charAt(id + 1) == ' ') ? 2 : 1));
        this.setNameQuestion(nameQs);
        this.setContentQuestion(contentQs);
    }
    // thêm đáp án key[0] thành đáp án đúng
    public boolean setKeyChoice(){
        for (Choice choice : choices) {
            if (choice.getName() == key.charAt(0)) {
                choice.setKey(true);
                choice.setPercentGrade(100);
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
                choice.setPercentGrade(100);
                return true;
            }
        }
        return false;
    }
    // show ra question
    public void show(){
        System.out.println(idQuestion+" "+nameQuestion+":"+contentQuestion+" "+imageDataQs);
        for (Choice choice : choices) {
            System.out.println(choice.getName() + " " + choice.getContentChoice() + " " + choice.isKey() + " " + choice.getImageDataChoice() +" "+ choice.getPercentGrade());
        }
        System.out.println(key);
    }


    public void setNameQuestion(String nameQuestion) {
        this.nameQuestion = nameQuestion;
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

    public String getNameQuestion() {
        return nameQuestion;
    }

    public String getKey() {
        String ans = "";
        int size = choices.size();
        int idChoice = 'A';
        for (Choice choice : choices) {
            if (choice.getPercentGrade() > 0) {
                ans += (char) idChoice + " ";
            }
            idChoice++;
        }
        return ans;
    }
    // thêm đáp án vào list ans, không có điểm
}