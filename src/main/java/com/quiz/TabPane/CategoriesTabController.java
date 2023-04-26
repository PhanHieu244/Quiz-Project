package com.quiz.TabPane;

import com.DataManager.CategoryAPI;
import com.Question.Test;
import com.quiz.TabPane.QuestionTab.QuestionTabController;
import com.quiz.Tool.CategoriesBoxTool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoriesTabController implements Initializable {
    @FXML
    private Button add;

    @FXML
    private TextField idNumber;

    @FXML
    private TextArea info;

    @FXML
    private TextField name;

    @FXML
    private ComboBox<Test> parentCategory;

    @FXML
    void addCategory(ActionEvent event) {
        String nameString = name.getText();
        Test cateParent = parentCategory.getValue();
        Integer idParent;
        if (name.getText().equals("")) return;
        try{
            if (cateParent == null) idParent = null;
            else idParent = cateParent.getIdTest();
        }catch (NumberFormatException e){
            System.out.println("Loi nhap id");
            e.printStackTrace();
            return;
        }
        CategoryAPI.postNewCategory(nameString, info.getText(), idParent);
        reset();
    }

    private void reset(){
        idNumber.setText("");
        name.setText("");
        info.setText("");
        CategoriesBoxTool.resetAll();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CategoriesBoxTool.Setup(parentCategory);
    }
}
