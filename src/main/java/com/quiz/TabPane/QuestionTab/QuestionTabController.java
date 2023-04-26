package com.quiz.TabPane.QuestionTab;
import com.Question.Test;
import com.quiz.AddQuestion.AddQuestionController;
import com.quiz.MainUI.UIController;
import com.quiz.Tool.BaseController.QuestionTabBase;
import com.quiz.Tool.CategoriesBoxTool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class QuestionTabController extends QuestionTabBase {



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boxPath = "/com/quiz/TabPane/QuestionTab/QuestionBox.fxml";
        super.initialize(url, resourceBundle);
    }
    @FXML
    private void changeScene(ActionEvent event){
        if (categoriesBox.getValue() == null) return;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/quiz/AddQuestion/AddNewQuestion.fxml"));
            Node node = fxmlLoader.load();
            UIController.Instance.SetCenter(node);
            AddQuestionController addQuestionController = fxmlLoader.getController();
            addQuestionController.setup(categoriesBox.getValue());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void showList() {
        list.setVisible(true);
        super.showList();
    }
}
