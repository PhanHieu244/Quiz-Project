package com.quiz.QuizTab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class EditingQuizController implements Initializable {
    @FXML
    private ComboBox<String> addBut;
    @FXML
    private TextField maxGradeField;
    @FXML
    private Label questionLabel;
    @FXML
    private Label quizName;
    @FXML
    private Button saveBut;
    @FXML
    private Button selectMultiBut;
    @FXML
    private CheckBox sufferBox;
    @FXML
    private VBox vBox;
    @FXML
    private ComboBox<Integer> pageChoose;

    private HashMap<HBox, Integer> map;

    private List<CheckBox> checkBoxList;

    //todo test
    @FXML
    private Button deleteBut;
    @FXML
    private Button test;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        map = new HashMap<>();
        checkBoxList = new ArrayList<>();
        addBut.getItems().addAll("a new question", "from question bank", " a random question");
        setupPageChoose(20);
        test.setOnAction(event -> {
            CheckBox checkBox = new CheckBox();
            checkBoxList.add(checkBox);
            Label label = new Label("int i");
            Button delete = new Button("Delete");
            HBox hBox = new HBox(checkBox, label, delete);
            vBox.getChildren().add(hBox);
        });
    }



    public void addQuestion(List<Integer> idList){

    }

    private void removeQuestion(){

    }

    public void loadData(){

    }

    public void setupPageChoose(int numberPage){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < numberPage; i++) {
            list.add(i+1);
        }
        pageChoose.getItems().addAll(list);
    }

    @FXML
    void delete(ActionEvent event) {
        vBox.getChildren().remove(deleteBut.getParent());
    }

    @FXML
    private void save(ActionEvent event) {

    }

    @FXML
    private void selectMultipleItems(ActionEvent event) {

    }

    @FXML
    private void suffer(ActionEvent event) {

    }

    @FXML
    private void switchScene(ActionEvent event){

    }
}
