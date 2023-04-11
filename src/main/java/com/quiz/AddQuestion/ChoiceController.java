package com.quiz.AddQuestion;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class ChoiceController implements Initializable {
    @FXML
    public TextArea choice;
    @FXML
    public Button importFile;
    @FXML
    public ComboBox<String> gradeBox;
    @FXML
    public Label choiceId;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gradeBox.getItems().addAll(
                "None", "100%", "90%", "83.33333%", "80%", "75%", "70%",
                "66.66667%", "60%", "50%", "40%", "33.33333%", "30%", "25%",
                "20%", "16.66667%", "14.28571%", "12.5%", "11.11111%", "10%",
                "5%", "-5%", "-10%", "-11.11111%", "-12.5%", "-14.28571%",
                "-16.66667%", "-20%", "-25%", "-30%", "-33.33333%", "-40%",
                "-50%", "-60%", "-66.66667%", "-70%", "-75%", "-80%", "-83.33333%"
        );
        gradeBox.setValue("None");
    }

    public void setChoiceId(int id){
        choiceId.setText("Choice " + id);
    }
}
