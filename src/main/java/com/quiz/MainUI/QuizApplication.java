package com.quiz.MainUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class QuizApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(QuizApplication.class.getResource("Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //todo: learn event, property, observer

    public static void main(String[] args) {
        launch();
    }
}