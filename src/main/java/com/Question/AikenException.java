package com.Question;


import com.quiz.Tool.AlertTool;
import javafx.scene.control.Alert;

public class AikenException extends Exception{
    public AikenException(int error) {
        System.out.println("error at " + error);
        AlertTool.showWrongAiken(error);
    }

}
