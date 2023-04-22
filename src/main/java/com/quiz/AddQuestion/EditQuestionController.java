package com.quiz.AddQuestion;

import com.Base64Convert.Base64Convert;
import com.DataManager.QuestionAPI;
import com.Question.Choice;
import com.Question.Question;
import javafx.event.ActionEvent;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EditQuestionController extends AddQuestionBase {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }


    public void loadData(int idCate, int quesID){
        try {
            questionSave = QuestionAPI.getQuestion(quesID, idCate);
            base64 = questionSave.getImageDataQs();
            loadQuesContent(questionSave);
            List<Choice> choices = questionSave.getChoices();
            for (Choice choice : choices) {

                LoadChoice(choice);
            }
            int remain = 5 - choices.size();
            if (remain <= 0) moreChoices.setVisible(false);
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
        imageView.setImage(Base64Convert.base64ToImage(question.getImageDataQs()));
        //todo show video
    }

    private void putQuestion(){
        QuestionAPI.putNewQuestion(creatQuestion());
    }

    @Override
    protected void saveOut(ActionEvent event) {
        putQuestion();
        out();
    }

    @Override
    protected void saveContinue(ActionEvent event) {
        putQuestion();
    }

}


