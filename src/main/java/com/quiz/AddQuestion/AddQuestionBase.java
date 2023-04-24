package com.quiz.AddQuestion;

import com.Base64Convert.Base64Convert;
import com.Question.Choice;
import com.Question.Question;
import com.Question.Test;
import com.quiz.MainUI.UIController;
import com.quiz.TabPane.SettingTab;
import com.quiz.Tool.CategoriesBoxTool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public abstract class AddQuestionBase implements Initializable {
    @FXML
    protected VBox vBox;
    @FXML
    protected Button moreChoices;
    @FXML
    protected ComboBox<Test> categoriesBox;
    @FXML
    protected TextField questionName;
    @FXML
    protected TextArea questionText;
    @FXML
    protected TextField defaultMark;
    @FXML
    protected Button saveContinue;
    @FXML
    protected Button saveOut;
    @FXML
    protected Button cancel;
    @FXML
    protected ImageView imageView;
    @FXML
    protected Button importFile;
    @FXML
    protected MediaView mediaView;
    protected final FileChooser fileChooser = new FileChooser();
    protected ArrayList<ComboBox<String>> listGradeChoice = new ArrayList<>();
    protected ArrayList<TextArea> choicesText = new ArrayList<>();
    protected String base64;
    protected String[] base64Choices;

    protected Question questionSave;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        base64Choices = new String[5];
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif"),
                new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.mkv", "*.avi"),
                new FileChooser.ExtensionFilter("Text Files", "*.docx")
        );
        importFile.setOnAction(event -> {
            Stage stage = (Stage) importFile.getScene().getWindow();
            File file = fileChooser.showOpenDialog(stage);

            if (file != null){
                base64 = Base64Convert.encodeFileToBase64Binary(file);
                if (Base64Convert.isVideoFile(file)){
                    imageView.setImage(null);
                    Media media = new Media(file.toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaView.setMediaPlayer(mediaPlayer);
                    //System.out.println("media");
                    mediaPlayer.play();
                }else if (Base64Convert.isImageFile(file)) {
                    mediaView.setMediaPlayer(null);
                    Image image = new Image(file.toURI().toString());
                    imageView.setImage(image);
                }
                else {
                    System.out.println("File Name is not found");
                }
            }
        });
    }

    public void setup(Test test){
        CategoriesBoxTool.Setup(categoriesBox);
        categoriesBox.setValue(test);
    }



     protected void AddChoice(int amount){
        try{
            for (int i = 0; i < amount; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Choice.fxml"));
                Node node = fxmlLoader.load();
                ChoiceController controller = fxmlLoader.getController();
                int id = listGradeChoice.size();
                vBox.getChildren().add(id, node);
                controller.setChoiceId(id + 1);
                controller.addQuestionController = this;
                listGradeChoice.add(controller.gradeBox);
                choicesText.add(controller.choice);
                //todo add image
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void LoadChoice(Choice choice){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Choice.fxml"));
            Node node = fxmlLoader.load();
            ChoiceController controller = fxmlLoader.getController();
            int id = listGradeChoice.size();
            vBox.getChildren().add(id, node);
            base64Choices[id] = choice.getImageDataChoice();
            controller.addQuestionController = this;
            controller.setChoiceId(id + 1);
            controller.loadData(choice);
            listGradeChoice.add(controller.gradeBox);
            choicesText.add(controller.choice);
            //todo add image
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected float getPercent(String string){
        if (string.equals("None")) return 0;
        String percentString = string;
        percentString = percentString.replaceAll("%", ""); // remove the percent symbol
        return Float.parseFloat(percentString);
    }




    protected void out(){
        UIController.Instance.openTabPane(SettingTab.QuestionTab);
    }

    @FXML
    protected void saveContinue(ActionEvent event){

    }

    @FXML
    protected void saveOut(ActionEvent event){
        out();
    }

    protected Question creatQuestion(){
        List<Choice> choices = new ArrayList<>();
        List<Choice> choicesSave = questionSave.getChoices();
        for (int i = 0; i < choicesText.size(); i++) {
            TextArea choiceText = choicesText.get(i);
            String choiceContent = choiceText.getText();
            if (choiceContent.equals("")) continue;
            float percent = getPercent(listGradeChoice.get(i).getValue());
            Integer idChoice = null;
            if (i < choicesSave.size()) idChoice = choicesSave.get(i).id;
            Choice choice = new Choice(idChoice ,choiceContent, base64Choices[i], percent);
            choices.add(choice);
        }
        return new Question(questionText.getText(), base64, choices, questionSave.idQuestion);
    }

    @FXML
    protected void cancel(ActionEvent event){
        out();
    }
}
