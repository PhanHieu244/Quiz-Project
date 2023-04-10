package com.quiz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UIController implements Initializable {
    @FXML
    private BorderPane main;
    @FXML
    private Node settingTabPane;
    @FXML
    private Node addQuestion;
    @FXML
    public Button settingBut;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SetupNode();
        //main.setCenter(settingTabPane);
    }

    private void SetupNode(){
        try {
            settingTabPane = new FXMLLoader(getClass().getResource("SettingTabPane.fxml")).load();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ShowSettingPopUp(ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SettingPopUp.fxml"));
            Parent root = fxmlLoader.load();
            SettingPopUpController settingPopUpController = fxmlLoader.getController();
            settingPopUpController.mainController = this;
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void SetCenter(Node node){
        main.setCenter(node);
    }
}