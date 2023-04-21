package com.quiz.TabPane.QuestionTab;

import com.Question.Test;
import com.quiz.AddQuestion.EditQuestionController;
import com.quiz.MainUI.UIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

import java.io.IOException;


public class QuestionBoxController {
    @FXML
    public CheckBox checkBox;
    @FXML
    private Label questionText;
    @FXML
    private Hyperlink edit;

    private Test category;
    private int quesID;

    public void setID(Test category, int quesID){
        this.category = category;
        this.quesID = quesID;
    }

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

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/quiz/AddQuestion/EditQuestion.fxml"));
            Node node = fxmlLoader.load();
            EditQuestionController controller = fxmlLoader.getController();
            UIController.Instance.SetCenter(node);
            controller.loadData(category.getIdTest(), quesID);
            controller.setup(category);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setText(String string){
        questionText.setText(string);
    }
}
