package com.quiz.Tool.BaseController;

import com.DataManager.QuestionAPI;
import com.Question.Question;
import com.Question.Test;
import com.quiz.TabPane.QuestionTab.QuestionBoxController;
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

public class QuestionListBase implements Initializable {
    @FXML
    protected VBox vBox;
    @FXML
    protected CheckBox seclectAll;
    @FXML
    protected ArrayList<CheckBox> listCheckBox = new ArrayList<CheckBox>();

    protected String boxPath;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    public void Show(Test test, boolean isShowSubCate){
        ArrayList<Question> questionsContent;
        try {
            questionsContent = QuestionAPI.getQuestionsContent(test.getIdTest());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            for (Question question : questionsContent) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(boxPath));
                Node node = fxmlLoader.load();
                QuestionBoxController controller = fxmlLoader.getController();
                listCheckBox.add(controller.checkBox);
                controller.setText(question.getContentQuestion());
                controller.setID(test, question.getIdQuestion());
                vBox.getChildren().add(node);
            }
            if (isShowSubCate){
                for (Test subCate: test.getChildren()){
                    Show(subCate, isShowSubCate);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("loi roi");
        }
    }



    @FXML
    protected void selectAllQuestion(ActionEvent event){
        boolean isSelected = seclectAll.isSelected();
        for (CheckBox checkBox : listCheckBox) {
            checkBox.setSelected(isSelected);
        }
    }

}
