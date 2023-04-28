package com.quiz.Tool.BaseController;

import com.Question.Question;
import com.quiz.QuizTab.EditingQuizController;
import javafx.stage.Stage;

import java.util.List;

public class QuestionAddTab extends QuestionTabBase{
    protected EditingQuizController editController;

    public void setEditController(EditingQuizController edit){
        editController = edit;
    }

    protected void close(List<Question> addToQuiz){
        editController.addQuestion(addToQuiz);
        ((Stage) selectAll.getScene().getWindow()).close();
    }
}
