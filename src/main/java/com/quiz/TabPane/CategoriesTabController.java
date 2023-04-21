package com.quiz.TabPane;

import com.DataManager.CategoryAPI;
import com.quiz.TabPane.QuestionTab.QuestionTabController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CategoriesTabController {
    @FXML
    private Button add;

    @FXML
    private TextField idNumber;

    @FXML
    private TextArea info;

    @FXML
    private TextField name;

    @FXML
    private ComboBox<String> parentCategory;

     public QuestionTabController quesCtrl;

    @FXML
    void addCategory(ActionEvent event) {
        String nameString = name.getText();
        String idString = idNumber.getText();
        int id;
        if (name.getText().equals("")) return;
        try{
            if (idString.equals("")) id = 0;
            else id = Integer.parseInt(idString);
        }catch (NumberFormatException e){
            System.out.println("Loi nhap id");
            e.printStackTrace();
            return;
        }
        CategoryAPI.postNewCategory(nameString, info.getText(), id);
        quesCtrl.Setup();
        reset();
    }

    private void reset(){
        idNumber.setText("");
        name.setText("");
        info.setText("");
        //todo reset parent
    }

}
