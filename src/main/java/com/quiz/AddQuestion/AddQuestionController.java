package com.quiz.AddQuestion;

import com.DataManager.QuestionAPI;
import com.Question.Choice;
import com.Question.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddQuestionController extends AddQuestionBase {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AddChoice(2);
        base64Choices = new String[5];
        moreChoices.setOnAction(event -> {
            AddChoice(3);
            moreChoices.setVisible(false);
        });
        super.initialize(url, resourceBundle);
    }

    @FXML
    private void addNewQuestion(ActionEvent event){
        List<Choice> choices = new ArrayList<>();
        for (int i = 0; i < choicesText.size(); i++) {
            TextArea choiceText = choicesText.get(i);
            String choiceContent = choiceText.getText();
            if (choiceContent.equals("")) continue;
            float percent = getPercent(listGradeChoice.get(i).getValue());
            Choice choice = new Choice(choiceContent, base64Choices[i], percent);
            choices.add(choice);
        }
        Question question = new Question(questionText.getText(), base64, choices);
        QuestionAPI.postNewQuestion(1, question);
        //todo add category
    }
}
