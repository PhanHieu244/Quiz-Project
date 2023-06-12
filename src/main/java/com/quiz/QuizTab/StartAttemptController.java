package com.quiz.QuizTab;

import com.Question.Quiz;
import com.quiz.Exam.QuizController;
import com.quiz.MainUI.UIController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartAttemptController {
    @FXML
    private Button cancelBut;

    @FXML
    private Button startBut;

    @FXML
    private Text text;
    public void setup(Quiz quiz, String time, Stage stage){
        startBut.setOnAction(event -> {
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/quiz/Exam/Quiz.fxml"));
                Parent root = fxmlLoader.load();
                QuizController quizController = fxmlLoader.getController();
                quizController.LoadQuiz(quiz);
                UIController.Instance.SetCenter(root);
                stage.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
        cancelBut.setOnAction(e -> {
            stage.close();
        }
        );
        text.setText("Your attempt will have a time limit of " + time
                + ". When you start, the timer will begin to count down and cannot be paused. You must finish your attempt before it expires. Are you sure you wish to start now?");
    }
}
