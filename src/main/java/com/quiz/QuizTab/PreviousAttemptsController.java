package com.quiz.QuizTab;

import com.DataManager.QuestionAPI;
import com.Question.Question;
import com.Question.Test;
import com.quiz.Exam.QuestionController;
import com.quiz.Exam.QuizController;
import com.quiz.MainUI.UIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.List;

public class PreviousAttemptsController {

    @FXML
    private Label nameQuiz;

    @FXML
    private ComboBox<?> pageChoose;

    @FXML
    private Button previewBut;

    @FXML
    private Button settingBut;

    @FXML
    private Label timeLitmitLabel;

    private Test quiz; //todo Quiz

    @FXML
    void openEdit(ActionEvent event) {

    }

    @FXML
    void showPreview(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/quiz/Exam/Quiz.fxml"));
            Parent root = fxmlLoader.load();
            QuizController quizController = fxmlLoader.getController();
            quizController.LoadQuiz(quiz);
            UIController.Instance.SetCenter(root);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Setup(Test quiz){ // todo Quiz
        this.quiz = quiz;
        nameQuiz.setText(quiz.getNameTest());
    }

}