package com.quiz.AddQuestion;

import com.Base64Convert.Base64Convert;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;

public class ChoiceController implements Initializable {
    @FXML
    public TextArea choice;
    @FXML
    public Button importFile;
    @FXML
    public ComboBox<String> gradeBox;
    @FXML
    public Label choiceId;
    @FXML
    public ImageView imageView;

    private final FileChooser fileChooser = new FileChooser();

    private  FileInputStream fileInputStream;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gradeBox.getItems().addAll(
                "None", "100%", "90%", "83.33333%", "80%", "75%", "70%",
                "66.66667%", "60%", "50%", "40%", "33.33333%", "30%", "25%",
                "20%", "16.66667%", "14.28571%", "12.5%", "11.11111%", "10%",
                "5%", "-5%", "-10%", "-11.11111%", "-12.5%", "-14.28571%",
                "-16.66667%", "-20%", "-25%", "-30%", "-33.33333%", "-40%",
                "-50%", "-60%", "-66.66667%", "-70%", "-75%", "-80%", "-83.33333%"
        );
        gradeBox.setValue("None");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif")
        );
        importFile.setOnAction(event -> {
            Stage stage = (Stage) importFile.getScene().getWindow();
            File file = fileChooser.showOpenDialog(stage);
            if (file != null){
                choice.setText(Base64Convert.encodeFileToBase64Binary(file));
                Image image = new Image(file.toURI().toString());

                imageView.setImage(image);
                /*try {
                    Image image = loadImage(file);
                    imageView.setImage(image);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }*/

            }
        });

    }

    public void setChoiceId(int id){
        choiceId.setText("Choice " + id);
    }


    private Image loadImage(File file) throws FileNotFoundException{
        FileInputStream stream = new FileInputStream(file);
        return new Image(stream);
    }




}
