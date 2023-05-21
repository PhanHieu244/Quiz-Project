package com.quiz.Exam;

import com.Question.Choice;
import com.Question.Question;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestionController {
    @FXML
    private Label idQuestionLabel;
    @FXML
    private VBox questionBox;
    @FXML
    private Label questionLabel;
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
    private float marks;

    public AnchorPane loadQues(Question question, int id, Pane donePane){
        gradeMap = new HashMap<>();
        correctChoice = new ArrayList<>();
        idQuestionLabel.setText("Question " + id);
        this.donePane = donePane;
        questionLabel.setText(question.getNameQuestion() + ": " + question.getContentQuestion());
        choiceList = new ArrayList<>();
        char idChoice = 'a';
        if(question.isMultiChoice()){
            for (Choice choice :
                    question.getChoices()) {
                CheckBox checkBox = new CheckBox();
                setChoiceButton(checkBox, idChoice, choice);
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
            donePane.setStyle("-fx-background-color: #5b5858;");
        });

        float grade = choice.getPercentGrade();
        String content = choice.getContentChoice();
        button.setText((char)idChoice + ":  " + content);
        button.setFont(Font.font(fontChoice));
        button.setPadding(new Insets(10, 0, 0 , 25));
        questionBox.getChildren().add(button);
        gradeMap.put(button, grade);
        if (grade > 0) correctChoice.add(content);
        choiceList.add(button);
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
        Label correctLabel = new Label(correctString.toString());
        correctLabel.setFont(Font.font(fontChoice));
        correctLabel.setPadding(new Insets(10, 0, 12, 15));
        correctLabel.setPrefSize(widthLabel, heightLabel);

        if (marks < 1) {
            correctLabel.setStyle("-fx-background-color: #ffe4b6; -fx-text-fill: #d38810;");
            donePane.setStyle("-fx-background-color: #ec0606;");
        } else {
            correctLabel.setStyle("-fx-background-color: #17c278; -fx-text-fill: #0d4307;");
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
