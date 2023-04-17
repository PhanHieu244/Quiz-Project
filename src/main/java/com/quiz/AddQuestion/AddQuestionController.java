package com.quiz.AddQuestion;

import com.Base64Convert.Base64Convert;
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
import java.util.ResourceBundle;

public class AddQuestionController implements Initializable {
    @FXML
    private VBox vBox;
    @FXML
    private Button moreChoices;
    @FXML
    private ComboBox<String> categoriesBox;
    @FXML
    private TextField questionName;
    @FXML
    private TextArea questionText;
    @FXML
    private TextField defaultMark;
    @FXML
    private Button saveContinue;
    @FXML
    private Button saveOut;
    @FXML
    private Button cancel;
    @FXML
    public ImageView imageView;
    @FXML
    private Button importFile;
    @FXML
    private MediaView mediaView;

    private final FileChooser fileChooser = new FileChooser();

    private ArrayList<ComboBox<String>> listGradeChoice = new ArrayList<ComboBox<String>>();
    private ArrayList<TextArea> choicesText = new ArrayList<TextArea>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AddChoice(2);
        moreChoices.setOnAction(event -> {
            AddChoice(3);
            moreChoices.setVisible(false);
        });

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif"),
                new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.mkv", "*.avi"),
                new FileChooser.ExtensionFilter("Text Files", "*.docx")
        );
        importFile.setOnAction(event -> {
            Stage stage = (Stage) importFile.getScene().getWindow();
            File file = fileChooser.showOpenDialog(stage);

            if (file != null){
                questionText.setText(Base64Convert.encodeFileToBase64Binary(file));
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
        //todo: test

    }

    private void AddChoice(int amount){
        try{
            for (int i = 0; i < amount; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Choice.fxml"));
                Node node = fxmlLoader.load();
                ChoiceController controller = fxmlLoader.getController();
                int id = listGradeChoice.size();
                vBox.getChildren().add(id, node);
                controller.setChoiceId(id + 1);
                listGradeChoice.add(controller.gradeBox);
                choicesText.add(controller.choice);
                //todo add image
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private float getPercent(String string){
        if (string.equals("None")) return 0;
        String percentString = string;
        percentString = percentString.replaceAll("%", ""); // remove the percent symbol
        return Float.parseFloat(percentString);
    }

}
