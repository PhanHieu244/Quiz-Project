package com.quiz.QuizTab.EditQuiz;

import com.quiz.Tool.BaseController.QuestionTabBase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;


public class ShowBankQuesController extends QuestionTabBase {
    @FXML
    private Button addSelectedBut;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boxPath = ""; //todo
        super.initialize(url, resourceBundle);
    }
}
