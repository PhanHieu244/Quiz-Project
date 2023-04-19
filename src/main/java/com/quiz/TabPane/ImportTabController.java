package com.quiz.TabPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ImportTabController implements Initializable {

    @FXML
    private Button choose;

    @FXML
    private Button importBut;

    private File file;

    @FXML
    private void chooseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(null);
    }

    @FXML
    private void importFile(ActionEvent event) {
        if (file == null) {
            System.out.println("File is not found");
            return;
        }
        if (!CheckFileText(file.getName())) {
            showWarning();
            return;
        }
        showMessageBox();
    }

    private void showWarning(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setContentText("Wrong Format");
        alert.showAndWait();
    }

    private void showWrongAiken(int i){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setContentText("Error at" + i);
        alert.showAndWait();
    }

    private void showMessageBox(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("OK");
        alert.setTitle("Message Box");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //fileChooser.getExtensionFilters().add
    }

    private boolean CheckFileText(String fileName){
        return fileName.endsWith(".txt") || fileName.endsWith(".doc") || fileName.endsWith(".docx");
    }
}
