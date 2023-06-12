package com.Question;

import java.util.List;

public class Quiz {
    private final Integer idQuiz;
    private String name;
    private int minutes;
    private List<Question> questions;

    private boolean isSuffer;

    public Quiz() {
        idQuiz = null;
    }

    public Quiz(Integer id, String name, Integer minutes){
        idQuiz = id;
        this.name = name;
        this.minutes = minutes;
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

    public void setSuffer(boolean suffer) {
        isSuffer = suffer;
    }
}
