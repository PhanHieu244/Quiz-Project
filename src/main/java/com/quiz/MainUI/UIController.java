package com.quiz.MainUI;
import com.quiz.TabPane.SettingController;
import com.quiz.TabPane.SettingTab;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UIController implements Initializable {

    public static UIController Instance;
    @FXML
    private BorderPane main;
    @FXML
    private Node addQuestion;
    @FXML
    public Button settingBut;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Instance == null){
            Instance = this;
        }
        SetupNode();
    }

    private void SetupNode(){
        try {
            Node quizList = new FXMLLoader(getClass().getResource("/com/quiz/QuizTab/QuizList.fxml")).load();
            main.setCenter(quizList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void ShowSettingPopUp(ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/quiz/TabPane/SettingPopUp.fxml"));
            Parent root = fxmlLoader.load();
            //SettingPopUpController settingPopUpController = fxmlLoader.getController();
            //settingPopUpController.mainController = this;
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(settingBut.getScene().getWindow());
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void SetCenter(Node node){
        main.setCenter(node);
    }

    public void openTabPane(SettingTab settingTab){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/quiz/TabPane/SettingTabPane.fxml"));
            Parent root = fxmlLoader.load();
            SettingController settingController = fxmlLoader.getController();
            SetCenter(root);
            settingController.ShowTab(settingTab);
            settingBut.setVisible(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}