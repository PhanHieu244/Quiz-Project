package com.quiz.Exam;

import com.DataManager.QuestionAPI;
import com.Question.Question;
import com.Question.Quiz;
import com.Question.Test;
import com.quiz.MainUI.MainTab;
import com.quiz.MainUI.UIController;
import com.quiz.Tool.BaseController.CountdownTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuizController{

    @FXML
    private Hyperlink finishBut;

    @FXML
    private VBox listQuesBox;

    @FXML
    private VBox quizNavigation;

    @FXML
    private Label timeLabel;

    @FXML
    private VBox vBox;
    @FXML
    private VBox box;

    private List<QuestionController> QuesControllerList;
    private List<Pane> DonePaneList;
    List<Question> questions;

    private float totalMarks = 0f;
    private boolean isFinish = false;
    private final float widthHbox = 370f;
    private final float heightHbox = 40;
    private final float widthNaviBox = 28f;
    private final float heightNaviBox = 32f;
    private CountdownTimer countdownTimer;

    private Quiz quiz;

    public void LoadQuiz(Quiz quiz){
        UIController.Instance.openPreview();
        this.quiz = quiz;
        countdownTimer = new CountdownTimer(quiz.getMinutes(), this);
        countdownTimer.createCountdownLabel(timeLabel);
        QuesControllerList = new ArrayList<>();
        DonePaneList = new ArrayList<>();
        questions = QuestionAPI.getQuestionQuiz(quiz);
        creatQuizNavigation(questions);

        int i = 1;
        try {
            for (Question question :
                    questions) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("QuestionInExam.fxml"));
                fxmlLoader.load();
                QuestionController questionController = fxmlLoader.getController();
                listQuesBox.getChildren().
                        add(questionController.loadQues(question, i, DonePaneList.get(i - 1)));
                QuesControllerList.add(questionController);
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void creatQuizNavigation(List<Question> questions){
        int size = questions.size();
        System.out.println("day la size "+ size);
        int sizeBox = (size - 1) / 8;
        for (int i = 0; i <= sizeBox; i++) {
            HBox hBox = new HBox();
            hBox.setSpacing(5);
            hBox.setPadding(new Insets(0, 0, 0 , 5));
            hBox.setPrefSize(widthHbox, heightHbox);
            hBox.setMinSize(widthHbox, heightHbox);
            int minSize = Math.min((i + 1) * 8, size);
            for (int j = i * 8; j < minSize; j++) {
                VBox naviBox = new VBox();
                naviBox.setPrefSize(widthNaviBox, heightNaviBox);
                naviBox.setAlignment(Pos.CENTER);
                naviBox.setStyle("-fx-border-color: #000000;");
                Label label = new Label(Integer.toString(j + 1));
                Pane pane = new Pane();
                pane.setPrefSize(widthNaviBox, heightNaviBox);
                DonePaneList.add(pane);
                System.out.println("day la "+ i);
                naviBox.getChildren().addAll(label ,pane);
                hBox.getChildren().add(naviBox);
            }
            quizNavigation.getChildren().add(hBox);
        }
    }



    @FXML
    public void finish(){
        if(!isFinish){
            for (QuestionController controller :
                    QuesControllerList) {
                totalMarks += controller.finishQuiz();
            }
            vBox.getChildren().remove(0);
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Marks.fxml"));
                Parent root = fxmlLoader.load();
                MarksController marksController = fxmlLoader.getController();
                marksController.setup(countdownTimer.getTimeTaken(), totalMarks, questions.size(), quiz.getGrade());
                vBox.getChildren().add(0, root);
            } catch (Exception e){
                e.printStackTrace();
            }
            finishBut.setText("Finish review");
            isFinish = true;
        } else {
            UIController.Instance.setMainUI(MainTab.QuizList);
        }
    }

}
