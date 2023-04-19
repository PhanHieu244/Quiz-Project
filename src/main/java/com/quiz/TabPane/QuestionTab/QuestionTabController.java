package com.quiz.TabPane.QuestionTab;

import com.DataManager.APIConnector;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuestionTabController implements Initializable {
    @FXML
    private ComboBox<String> categoriesBox;
    @FXML
    private VBox vBox;
    @FXML
    private Button createQuestion;

    private Node list;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Setup();

        categoriesBox.setOnAction(event -> {
            try{
                vBox.getChildren().remove(list);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/quiz/TabPane/QuestionTab/QuestionList.fxml"));
                list = fxmlLoader.load();
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
        ObservableList<String> categories = FXCollections.observableArrayList();
        try {
            String[] names = CategoryAPI.getName();
            categories.addAll(names);
            categoriesBox.setItems(categories);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
