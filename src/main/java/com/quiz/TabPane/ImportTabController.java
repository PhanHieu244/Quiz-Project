package com.quiz.TabPane;

import com.DataManager.QuestionAPI;
import com.Question.*;
import com.quiz.Tool.AlertTool;
import com.quiz.Tool.CategoriesBoxTool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ImportTabController implements Initializable {
    @FXML
    protected ComboBox<Test> categoriesBox;
    @FXML
    private Button choose;
    @FXML
    private Button importBut;
    @FXML
    private Label nameLabel;

    private File file;

    @FXML
    private void chooseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(null);
        if (file == null) return;
        nameLabel.setText("File Name: " + file.getName());
    }

    @FXML
    private void importFile(ActionEvent event) {
        if (file == null) {
            AlertTool.showWarning("Error: File not found!");
            return;
        }
        String nameFile = file.getName();
        if (!CheckFileText(nameFile)) {
            AlertTool.showWarning("Wrong Format");
             return;
        }
        if (categoriesBox.getValue() == null) {
            AlertTool.showWarning("Error: Category not found!");
            return;
        }
        String path = file.getPath();
        ReaderQuestion readerQuestion = nameFile.endsWith(".txt") ? new ReaderQsTxt(path) : new ReaderQsWord(path);
        Test category = readerQuestion.read();
        QuestionAPI.postListQuestion
                (categoriesBox.getValue().getIdTest(), category.getQuestions());
        AlertTool.showMessageBox();
        reset();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //fileChooser.getExtensionFilters().add
        CategoriesBoxTool.Setup(categoriesBox);
    }

    private boolean CheckFileText(String fileName){
        return fileName.endsWith(".txt") || fileName.endsWith(".doc") || fileName.endsWith(".docx");
    }

    private void reset(){
        CategoriesBoxTool.resetAll();
        nameLabel.setText("File Name: null");
        file = null;
    }
}
