package com.Question;


import javafx.scene.control.Alert;

public class AikenException extends Exception{
    public AikenException(int error) {
        System.out.println("error at " + error);
        showWrongAiken(error);
    }

    private void showWrongAiken(int i){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setContentText("Error at " + i);
        alert.showAndWait();
    }
}
