package com.quiz.TabPane.QuestionTab;

import com.DataManager.QuestionAPI;
import com.Question.Question;
import com.Question.Test;
import com.quiz.Tool.BaseController.QuestionListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class QuestionListController extends QuestionListBase {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boxPath = "QuestionBox.fxml";
    }


}
