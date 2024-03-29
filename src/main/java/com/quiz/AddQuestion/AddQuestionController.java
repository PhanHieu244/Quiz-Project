package com.quiz.AddQuestion;

import com.DataManager.QuestionAPI;
import com.Question.Choice;
import com.Question.Question;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddQuestionController extends AddQuestionBase {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        questionSave = new Question();
        AddChoice(2);
        base64Choices = new String[5];
        moreChoices.setOnAction(event -> {
            AddChoice(3);
            moreChoices.setVisible(false);
        });
        super.initialize(url, resourceBundle);
    }

    protected void pushQuestion() {
        QuestionAPI.postNewQuestion(categoriesBox.getValue().getIdTest(), questionSave);
    }


}
