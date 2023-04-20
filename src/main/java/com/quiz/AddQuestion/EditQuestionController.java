package com.quiz.AddQuestion;

import com.Base64Convert.Base64Convert;
import com.DataManager.QuestionAPI;
import com.Question.Choice;
import com.Question.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditQuestionController extends AddQuestionBase {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }



    public void loadData(int idCate, int quesID){
        try {
            Question question = QuestionAPI.getQuestion(quesID, idCate);
            loadQuesContent(question);
            List<Choice> choices = question.getChoices();
            for (Choice choice : choices) {
                LoadChoice(choice);
            }
            int remain = 5 - choices.size();
            if (remain < 0) moreChoices.setVisible(false);
            else {
                moreChoices.setText("BLANKS FOR " + remain + " MORE CHOICE");
                moreChoices.setOnAction(event -> {
                    AddChoice(remain);
                    moreChoices.setVisible(false);
                });
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadQuesContent(Question question){
        questionText.setText(question.getContentQuestion());
        imageView.setImage(Base64Convert.base64ToImage(base64));
        //todo show video
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
        QuestionAPI.postNewQuestion(2, question);
    }
}
