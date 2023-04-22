package com.quiz.QuizTab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EditingQuizController implements Initializable {
    @FXML
    private ComboBox<String> addBut;
    @FXML
    private TextField maxGradeField;
    @FXML
    private Label questionLabel;
    @FXML
    private Label quizName;
    @FXML
    private Button saveBut;
    @FXML
    private Button selectMultiBut;
    @FXML
    private CheckBox sufferBox;
    @FXML
    private VBox vBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addBut.getItems().addAll("a new question", "from question bank", " a random question");
    }
    @FXML
    private void save(ActionEvent event) {

    }

    @FXML
    private void selectMultipleItems(ActionEvent event) {

    }

    @FXML
    private void suffer(ActionEvent event) {

    }
    @FXML
    private void switchScene(ActionEvent event){

    }

    public void addQuestion(List<Integer> idList){

    }

    private void removeQuestion(){

    }

    public void loadData(){

    }


}
