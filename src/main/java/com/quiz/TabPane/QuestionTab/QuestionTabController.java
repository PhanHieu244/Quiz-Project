package com.quiz.TabPane.QuestionTab;

import com.DataManager.CategoryAPI;
import com.quiz.MainUI.UIController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class QuestionTabController implements Initializable {
    @FXML
    private ComboBox<String> categoriesBox;
    @FXML
    private VBox vBox;
    @FXML
    private Button createQuestion;

    private Node list;

    private HashMap<String, Integer> map;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Setup();

        categoriesBox.setOnAction(event -> {
            try{
                vBox.getChildren().remove(list);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/quiz/TabPane/QuestionTab/QuestionList.fxml"));
                list = fxmlLoader.load();
                QuestionListController listController = fxmlLoader.getController();
                listController.Show(map.get(categoriesBox.getValue()));
                vBox.getChildren().add(list);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
    @FXML
    private void changeScene(ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/quiz/AddQuestion/AddNewQuestion.fxml"));
            Node node = fxmlLoader.load();
            UIController.Instance.SetCenter(node);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Setup(){
        try {
            ObservableList<String> categories = FXCollections.observableArrayList();
            map = CategoryAPI.getMap();
            for(String name: map.keySet()){
                categories.add(name);
            }
            categoriesBox.setItems(categories);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
