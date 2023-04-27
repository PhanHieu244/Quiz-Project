package com.quiz.Tool;

import javafx.scene.control.Alert;

public class AlertTool {
    public static void showWarning(String content){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void showWrongAiken(int i){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setContentText("Error at" + i);
        alert.showAndWait();
    }

    public static void showMessageBox(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("OK");
        alert.setTitle("Message Box");
        alert.showAndWait();
    }
}
