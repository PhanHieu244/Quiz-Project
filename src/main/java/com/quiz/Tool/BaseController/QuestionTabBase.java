package com.quiz.Tool.BaseController;

import com.DataManager.QuestionAPI;
import com.Question.Question;
import com.Question.Test;
import com.quiz.TabPane.QuestionTab.QuestionBoxController;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class QuestionTabBase implements Initializable {
    @FXML
    protected ComboBox<Test> categoriesBox;
    @FXML
    protected ArrayList<CheckBox> listCheckBox = new ArrayList<CheckBox>();
    @FXML
    protected VBox vBox;
    @FXML
    protected Button createQuestion;
    @FXML
    protected CheckBox showCateQues;
    @FXML
    protected CheckBox seclectAll;
    @FXML
    protected VBox list;
    protected String boxPath;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Setup();
        showCateQues.setSelected(true);
        categoriesBox.setOnAction(event -> {
            showList();
        });
        showCateQues.setOnAction(event ->{
            showList();
        });
    }


    protected void showList(){
        if (categoriesBox.getValue() == null) return;
        vBox.getChildren().clear();
        showQuesList();
    }

    public void Setup(){
        list.setVisible(false);
        CategoriesBoxTool.Setup(categoriesBox);
    }

    protected void showQuesList(){
        if (categoriesBox.getValue() == null) return;
        Show(categoriesBox.getValue());
    }

    public void Show(Test test){
        ArrayList<Question> questionsContent;
        try {
            questionsContent = QuestionAPI.getQuestionsContent(test.getIdTest(), showCateQues.isSelected());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        showQuestions(questionsContent);
    }

    protected void showQuestions(ArrayList<Question> questionsContent){
        try {
            for (Question question : questionsContent) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(boxPath));
                Node node = fxmlLoader.load();
                QuestionBoxController controller = fxmlLoader.getController();
                listCheckBox.add(controller.checkBox);
                controller.setText(question.getContentQuestion());
                controller.setID(question.getIdQuestion());
                vBox.getChildren().add(node);
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
