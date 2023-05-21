package com.quiz.AddQuestion;

import com.Base64Convert.Base64Convert;
import com.DataManager.QuestionAPI;
import com.Question.Choice;
import com.Question.Question;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EditQuestionController extends AddQuestionBase {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }


    public void loadData(int quesID){
        try {
            questionSave = QuestionAPI.getQuestion(quesID);
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
        questionName.setText(question.getNameQuestion());
        questionText.setText(question.getContentQuestion());
        String base64String = question.getImageDataQs();
        if(base64String != null) {
            if (Base64Convert.isImage(base64String)) {
                imageView.setImage(Base64Convert.base64ToImage(base64String));
            } else {
                MediaPlayer mediaPlayer = new MediaPlayer(Base64Convert.base64ToMedia(base64String));
                mediaView.setMediaPlayer(mediaPlayer);
                mediaPlayer.play();
            }
        }
    }

    protected void pushQuestion(){
        QuestionAPI.putNewQuestion(questionSave);
    }


}


