package com.quiz.QuizTab;

import com.DataManager.QuizAPI;
import com.Question.Quiz;
import com.quiz.MainUI.UIController;
import com.quiz.MainUI.MainTab;
import com.quiz.Tool.AlertTool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewQuizController implements Initializable {

    @FXML
    private Button cancelBut;

    @FXML
    private Button createBut;

    @FXML
    private TextArea description;

    @FXML
    private CheckBox enableTime;

    @FXML
    private TextField name;

    @FXML
    private TextField time;

    @FXML
    private ComboBox<String> timeType;
    @FXML
    private ComboBox<String> expires;
    ObservableList<String> observableList = FXCollections.observableArrayList(
            "minutes", "hours"
    );

    private int timeCount = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UIController.Instance.SetFullSize();
        timeType.setItems(observableList);
        timeType.setValue("minutes");
        time.setText("0");
        createBut.setOnAction(event -> creatQuiz());
        cancelBut.setOnAction(event -> cancel());
        expires.setValue("Open attempts are submitted automatically");
    }

    public Button getCancelBut() {
        return cancelBut;
    }

    private int getMinutes(){

        if (timeType.getValue().equals("hours"))
            return timeCount * 60;
        return timeCount;
    }

    private void creatQuiz(){
        String inputText = time.getText();
        try {
            if (inputText.equals("0")) {
                AlertTool.showWarning("time greater than 0!!!");
                return;
            }
            // Parse the input text as an unsigned integer
            timeCount = Integer.parseUnsignedInt(inputText);
        } catch (NumberFormatException ex) {
            AlertTool.showWarning("Invalid input: Not a valid unsigned integer");
            return;
        }
        if (name.getText().equals("")){
            AlertTool.showWarning("name not found");
            return;
        }
        QuizAPI.postNewQuiz(name.getText(), getMinutes(), false);
        cancel();
    }

    private void cancel(){
        UIController.Instance.setMainUI(MainTab.QuizList);
    }
}
