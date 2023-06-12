package com.Question;

import java.util.List;

public class Quiz {
    private final Integer idQuiz;
    private String name;
    private int minutes;
    private float grade;


    private List<Question> questions;

    private boolean isSuffer;

    public Quiz() {
        idQuiz = null;
    }

    public Quiz(Integer id, String name, Integer minutes, Float grade){
        idQuiz = id;
        this.name = name;
        this.minutes = minutes;
        this.grade = grade;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getName() {
        return name;
    }

    public Integer getIdQuiz() {
        return idQuiz;
    }

    public int getMinutes() {
        return minutes;
    }

    public boolean isSuffer() {
        return isSuffer;
    }

    public float getGrade() {
        return grade;
    }
    public void setGrade(float grade) {
        this.grade = grade;
    }
    public void setSuffer(boolean suffer) {
        isSuffer = suffer;
    }
}
