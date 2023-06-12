package com.quiz.QuizTab;

import com.Question.Quiz;
import com.quiz.Exam.QuizController;
import com.quiz.MainUI.UIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PreviousAttemptsController {

    @FXML
    private Label nameQuiz;

    @FXML
    private ComboBox<?> pageChoose;

    @FXML
    private Button previewBut;

    @FXML
    private Button settingBut;

    @FXML
    private Label timeLitmitLabel;

    private Quiz quiz;
    String timeQuiz;

    @FXML
    void openEdit(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().
                    getResource("/com/quiz/QuizTab/EditQuiz/EditingQuiz.fxml"));
            Parent root = fxmlLoader.load();
            EditingQuizController quizController = fxmlLoader.getController();
            quizController.loadData(quiz);
            UIController.Instance.SetCenter(root);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void showPreview(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/quiz/QuizTab/StartAttempt.fxml"));
            Parent root = fxmlLoader.load();
            StartAttemptController controller = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Start attempt");
            stage.initOwner(settingBut.getScene().getWindow());
            controller.setup(quiz, timeQuiz, stage);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
        /*try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/quiz/Exam/Quiz.fxml"));
            Parent root = fxmlLoader.load();
            QuizController quizController = fxmlLoader.getController();
            quizController.LoadQuiz(quiz);
            UIController.Instance.SetCenter(root);
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }

    public void Setup(Quiz quiz){ // todo Quiz
        this.quiz = quiz;
        nameQuiz.setText(quiz.getName());
        UIController.Instance.openAttempt(quiz);
        timeQuiz = setTime();
        timeLitmitLabel.setText("Time limit: " + timeQuiz);
    }

    private String setTime(){
        int time = quiz.getMinutes();
        String type;
        if (time % 60 == 0){
            time /= 60;
            type = "hour";
        } else {
            type = "minutes";
        }
        return time + " " + type;
    }

}