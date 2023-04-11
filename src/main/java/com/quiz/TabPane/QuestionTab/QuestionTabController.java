package com.quiz.TabPane.QuestionTab;

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

    ObservableList<String> categories = FXCollections.observableArrayList(
            "300 bai code thieu nhi (300)",
            "Toi da code dao nhu nao (100)",
            "Lap trinh huong ve em (1)"
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoriesBox.setItems(categories);
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
}
