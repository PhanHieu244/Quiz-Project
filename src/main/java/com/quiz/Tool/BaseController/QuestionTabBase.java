package com.quiz.Tool.BaseController;

import com.Question.Test;
import com.quiz.AddQuestion.AddQuestionController;
import com.quiz.MainUI.UIController;
import com.quiz.TabPane.QuestionTab.QuestionListController;
import com.quiz.Tool.CategoriesBoxTool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class QuestionTabBase implements Initializable {
    @FXML
    protected ComboBox<Test> categoriesBox;
    @FXML
    protected VBox vBox;
    @FXML
    protected Button createQuestion;
    @FXML
    protected CheckBox showQues;

    protected Node list;

    protected String listPath;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Setup();
        showQues.setSelected(true);
        categoriesBox.setOnAction(event -> {
            showQuesList(showQues.isSelected());
        });
    }

    @FXML
    protected void showList(ActionEvent event){
        vBox.getChildren().remove(list);
        showQuesList(showQues.isSelected());
    }

    public void Setup(){
        vBox.getChildren().remove(list);
        CategoriesBoxTool.Setup(categoriesBox);
    }

    protected void showQuesList(boolean isShowSubCate){
        if (categoriesBox.getValue() == null) return;
        try{
            vBox.getChildren().remove(list);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(listPath));
            list = fxmlLoader.load();
            QuestionListController listController = fxmlLoader.getController();
            listController.Show(categoriesBox.getValue(), isShowSubCate);
            vBox.getChildren().add(list);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
