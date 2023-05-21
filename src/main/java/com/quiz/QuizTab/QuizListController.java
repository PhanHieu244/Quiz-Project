package com.quiz.QuizTab;

import com.DataManager.CategoryAPI;
import com.Question.Test;
import com.quiz.MainUI.UIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class QuizListController implements Initializable {
    @FXML
    private Button turnEditing;
    @FXML
    private VBox vbox;
    private List<Hyperlink> quizList;
    private final float fontName = 14f;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //todo get Test to Quiz
        List<Test> quizzes = CategoryAPI.getAllCategories();
        for(Test quiz : quizzes){
            Hyperlink quizName = new Hyperlink(quiz.getNameTest());
            quizName.setFont(Font.font(fontName));
            quizName.setPadding(new Insets(10, 0, 10,  10));
            quizName.setOnAction(event -> {
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PreviousAttempts.fxml"));
                    Parent root = fxmlLoader.load();
                    PreviousAttemptsController controller = fxmlLoader.getController();
                    controller.Setup(quiz);
                    UIController.Instance.SetCenter(root);
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
            vbox.getChildren().add(quizName);
        }
    }

    @FXML
    void showAddQuiz(ActionEvent event) {

    }
}
