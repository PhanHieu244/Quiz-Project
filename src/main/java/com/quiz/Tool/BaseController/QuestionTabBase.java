package com.quiz.Tool.BaseController;

import com.DataManager.QuestionAPI;
import com.Question.Question;
import com.Question.Test;
import com.quiz.MainUI.UIController;
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
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

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
    protected CheckBox selectAll;
    @FXML
    protected VBox list;
    protected ArrayList<Question> questions;
    protected String boxPath;

    protected final float minHeight = 46f;


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
        if (categoriesBox.getValue() == null) {
            list.setVisible(false);
            return;
        }
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
        try {
            questions = QuestionAPI.getAllQuestionInCate(test.getIdTest(), showCateQues.isSelected());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        showQuestions(questions);
    }

    protected void showQuestions(ArrayList<Question> questionsContent){
        try {
            for (Question question : questionsContent) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(boxPath));
                Node node = fxmlLoader.load();
                QuestionBoxController controller = fxmlLoader.getController();
                listCheckBox.add(controller.checkBox);
                controller.setText(setContent(question).getText());
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
        boolean isSelected = selectAll.isSelected();
        for (CheckBox checkBox : listCheckBox) {
            checkBox.setSelected(isSelected);
        }
    }


    protected Label setContent(Question question){
        Label label = new Label
                (question.getNameQuestion() + ": " + question.getContentQuestion());
        label.setPrefSize(725, minHeight);
        label.setMinHeight(minHeight);
        label.setFont(Font.font(14));
        return label;
    }
}
