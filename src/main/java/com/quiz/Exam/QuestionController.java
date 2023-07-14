package com.quiz.Exam;

import com.Base64Convert.Base64Convert;
import com.Question.Choice;
import com.Question.Question;
import com.Question.Test;
import com.quiz.Tool.AlertTool;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestionController {
    @FXML
    private Label idQuestionLabel;
    @FXML
    private VBox questionBox;
    @FXML
    private Text questionLabel;
    @FXML
    private AnchorPane main;
    @FXML
    private VBox Box;
    private ArrayList<ButtonBase> choiceList;
    private HashMap<ButtonBase, Float> gradeMap;
    private ArrayList<String> correctChoice;
    private Pane donePane;
    private final float fontChoice = 13f;
    private final float widthLabel = 731f;
    private final float heightLabel = 42f;
    private final float wrapWidth = 650f;
    private float marks;

    public AnchorPane loadQues(Question question, int id, Pane donePane){
        gradeMap = new HashMap<>();
        correctChoice = new ArrayList<>();
        idQuestionLabel.setText("Question " + id);
        this.donePane = donePane;
        String name = question.getNameQuestion();
        String text = (name == null || name.equals("")) ? "" : name + ": ";
        questionLabel.setText(text + question.getContentQuestion());
        String base64String = question.getImageDataQs();
        if(base64String != null) {
            if (Base64Convert.isImage(base64String)){
                ImageView imageView = new ImageView(Base64Convert.base64ToImage(base64String));
                questionBox.getChildren().add(imageView);
                VBox.setMargin(imageView, new Insets(0, 0, 0, 20));
            }else{
                MediaPlayer mediaPlayer = new MediaPlayer(Base64Convert.base64ToMedia(base64String));
                MediaView mediaView = new MediaView(mediaPlayer);
                VBox.setMargin(mediaView, new Insets(10, 0, 0, 55));
                Button play = new Button();
                play.setOnAction(event -> {
                    mediaPlayer.play();
                });
                play.setText("Play");
                Button pause = new Button();
                pause.setOnAction(event -> {
                    mediaPlayer.pause();
                });
                pause.setText("Pause");

                //pause.setStyle("-fx-font-size: #8e0707;");
                pause.setStyle("-fx-background-color: #e5dbdb;");
                pause.setStyle("-fx-text-fill: #e70808;");
                pause.setStyle("-fx-border-color: #8e0707;");
                play.setStyle("-fx-text-fill: #068af3;");
                play.setStyle("-fx-background-color: #e5dbdb;");
                play.setStyle("-fx-border-color: #043761;");

                HBox hBox = new HBox(play, pause);
                hBox.setSpacing(20);
                hBox.setPadding(new Insets(10, 0, 0, 80));

                //mediaPlayer.play();
                questionBox.getChildren().add(mediaView);
                questionBox.getChildren().add(hBox);

            }
        }
        //show choices
        choiceList = new ArrayList<>();
        char idChoice = 'a';
        if(question.isMultiChoice()){
            for (Choice choice :
                    question.getChoices()) {
                CheckBox checkBox = new CheckBox();
                setChoiceButton(checkBox, idChoice, choice);
                //checkBoxList.add(checkBox);
                idChoice++;
            }

        } else {
            ToggleGroup toggleGroup = new ToggleGroup();
            for (Choice choice :
                    question.getChoices()) {
                RadioButton radioButton = new RadioButton();
                radioButton.setToggleGroup(toggleGroup);
                setChoiceButton(radioButton, idChoice, choice);
                idChoice++;
            }
        }
        return main;
    }

    private void setChoiceButton(ButtonBase button, int idChoice, Choice choice){
        button.setOnAction(event -> {
            boolean off = true;
            if (button instanceof CheckBox){

                for (ButtonBase buttonBase : choiceList) {
                    CheckBox checkBox = (CheckBox) buttonBase;
                    if (checkBox.isSelected()) {
                        //AlertTool.showWarning("check");
                        off = false;
                        break;
                    }
                }
            }
            else off = false;
            if(off){
                donePane.setStyle("-fx-background-color: none");
            } else donePane.setStyle("-fx-background-color: #5b5858;");
        });
        button.setWrapText(true);
        button.setMaxWidth(wrapWidth);
        float grade = choice.getPercentGrade() / 100f;
        String content = choice.getContentChoice();
        Text text = new Text((char)idChoice + ".  " + content);
        text.setWrappingWidth(widthLabel);
        text.setFont(Font.font(fontChoice));
        text.setTranslateX(10);
        button.setGraphic(text);
        //button.setGraphicTextGap(10);
        //button.setText((char)idChoice + ":  " + content);
        //button.setFont(Font.font(fontChoice));
        button.setPadding(new Insets(10, 0, 0 , 25));
        questionBox.getChildren().add(button);
        gradeMap.put(button, grade);
        if (grade > 0) correctChoice.add(content);
        choiceList.add(button);
        String base64String = choice.getImageDataChoice();
        if(base64String != null) {
            if (Base64Convert.isImage(base64String)) {
                ImageView imageView = new ImageView(Base64Convert.base64ToImage(base64String));
                questionBox.getChildren().add(imageView);
                VBox.setMargin(imageView, new Insets(10, 0, 0, 55));
            }
        }
    }

    private float getMarks(){
        float grade = 0;
        for (ButtonBase button :
                choiceList) {
            if(button instanceof RadioButton){
                RadioButton radioButton = (RadioButton) button;
                if (radioButton.isSelected()){
                    grade += gradeMap.get(button);
                }
            }
            else {
                CheckBox checkBox = (CheckBox) button;
                if(checkBox.isSelected()){
                    grade += gradeMap.get(button);
                }
            }
        }
        return grade;
    }

    private void showAnswer(){
        StringBuilder correctString = new StringBuilder("The correct answer: ");
        int lastIndex = correctChoice.size() - 1;
        for (int i = 0; i < lastIndex; i++) {
            correctString.append(correctChoice.get(i)).append(", ");
        }
        correctString.append(correctChoice.get(lastIndex));
        Label correctLabel = new Label();
        Text text = new Text(correctString.toString());
        text.setWrappingWidth(widthLabel);
        text.setFont(Font.font(fontChoice));
        correctLabel.setGraphic(text);
        correctLabel.setPadding(new Insets(10, 0, 12, 15));
        correctLabel.setPrefSize(widthLabel, heightLabel);
        correctLabel.setMaxWidth(widthLabel);

        if (marks < 1) {
            text.setFill(Color.web("#d38810"));
            correctLabel.setStyle("-fx-background-color: #ffe4b6;");
            donePane.setStyle("-fx-background-color: #ec0606;");
        } else {
            text.setFill(Color.web("#0d4307"));
            correctLabel.setStyle("-fx-background-color: #17c278;");
            donePane.setStyle("-fx-background-color: #014617;");
        }

        Box.getChildren().add(correctLabel);
    }

    public float finishQuiz(){
        for (ButtonBase buttonBase : choiceList) {
            buttonBase.setDisable(true);
        }
        marks = getMarks();
        showAnswer();
        return marks;
    }
}
