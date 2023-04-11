package com.quiz.TabPane.QuestionTab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;


public class QuestionBoxController {
    @FXML
    public CheckBox checkBox;
    @FXML
    private Label questionText;
    @FXML
    private Hyperlink edit;


    @FXML
    private void selectBox(ActionEvent event){
        if (checkBox.isSelected()){
            System.out.println("chon cau nay");

        }else {
            System.out.println("khong chon");
        }
    }

    @FXML
    private void editQuestion(ActionEvent event){
        System.out.printf("change edit scene");
    }

    public void setText(String string){
        questionText.setText(string);
    }
}
