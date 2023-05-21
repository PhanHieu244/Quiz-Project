package com.Question;

import java.util.List;

public class Quiz {
    private final Integer idQuiz;
    private String name;
    private int minutes;
    private List<Question> questions;


    public Quiz() {
        idQuiz = null;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
