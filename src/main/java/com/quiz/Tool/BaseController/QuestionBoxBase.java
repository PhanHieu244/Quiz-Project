package com.quiz.Tool.BaseController;

import com.Question.Test;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;


public class QuestionBoxBase {
    @FXML
    public CheckBox checkBox;
    @FXML
    protected Label questionText;

    protected Test category;
    protected int quesID;

    public void setID(int quesID){
        this.quesID = quesID;
    }


    @FXML
    protected void selectBox(){
        if (checkBox.isSelected()){
            System.out.println("chon cau nay");

        }else {
            System.out.println("khong chon");
        }
    }


    public void setText(String string){
        questionText.setText(string);
    }
}
