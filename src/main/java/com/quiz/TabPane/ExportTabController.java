package com.quiz.TabPane;

import com.DataManager.QuestionAPI;
import com.Question.Question;
import com.Question.Test;
import com.Writer.Writer;
import com.quiz.Tool.CategoriesBoxTool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExportTabController implements Initializable {

    @FXML
    private ComboBox<Test> categoriesBox;

    @FXML
    private Button exportBut;
    @FXML
    private Button choose;

    @FXML
    private CheckBox isSetPass;

    @FXML
    private PasswordField passField;

    @FXML
    private CheckBox showCateQues;
    @FXML
    private TextField fileName;
    private DirectoryChooser directoryChooser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CategoriesBoxTool.Setup(categoriesBox);
        isSetPass.setOnAction(event -> {
            passField.setVisible(isSetPass.isSelected());
        });
    }

    @FXML
    private void exportFile(ActionEvent event) {
        directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory != null) {
            // The user has selected a directory, so now we can save the file there
            // For example:
            // File file = new File(selectedDirectory.getAbsolutePath() + "/myFile.txt");
            // // Write data to the file
            ArrayList<Question> questionsContent;
            try {
                questionsContent = QuestionAPI.getAllQuestionInCate
                        (categoriesBox.getValue().getIdTest(), showCateQues.isSelected());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Writer writer = new Writer(selectedDirectory.getAbsolutePath() + "/" + fileName.getText() + ".pdf");
            writer.PDFWrite(questionsContent);
        }
    }

    @FXML
    void chooseFolder(ActionEvent event) {

    }
}
