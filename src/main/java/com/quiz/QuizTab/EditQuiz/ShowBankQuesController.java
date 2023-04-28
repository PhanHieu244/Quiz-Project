package com.quiz.QuizTab.EditQuiz;

import com.Question.Question;
import com.quiz.Tool.BaseController.QuestionTabBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ShowBankQuesController extends QuestionTabBase {
    @FXML
    private Button addSelectedBut;

    private List<Question> listIDs;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listIDs = new ArrayList<>();
        addSelectedBut.setOnAction(event -> {
            System.out.println(listIDs);
            //todo
        });
        super.initialize(url, resourceBundle);
    }

    @Override
    protected void showQuestions(ArrayList<Question> questionsContent) {
        for (Question question: questionsContent){
            CheckBox checkBox = new CheckBox();
            checkBox.setOnAction(event -> {
                if (checkBox.isSelected()) listIDs.add(question);
                else listIDs.remove(question);
            });
            listCheckBox.add(checkBox);
            checkBox.setMinHeight(minHeight);
            HBox hBox = new HBox(checkBox ,setContent(question));
            //hBox.setPadding(new Insets(0,0,0,10));
            hBox.setSpacing(15);
            hBox.setMinHeight(hBox.getPrefHeight());
            hBox.setAlignment(Pos.CENTER_LEFT);
            vBox.getChildren().add(hBox);
        }
    }

    @Override
    protected void selectAllQuestion(ActionEvent event) {
        super.selectAllQuestion(event);
        listIDs.clear();
        if (selectAll.isSelected()){
            listIDs.addAll(questions);
        }
    }

    @Override
    protected void showList() {
        list.setVisible(true);
        selectAll.setSelected(false);
        listIDs.clear();
        listCheckBox.clear();
        super.showList();
    }

}
