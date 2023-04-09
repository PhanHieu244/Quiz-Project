package com.quiz;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import java.net.URL;
import java.util.ResourceBundle;

public class UIController implements Initializable {
    @FXML
    private BorderPane main;
    @FXML
    private Node settingTabPane;
    @FXML
    private Node addQuestion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SetupNode();
        main.setCenter(settingTabPane);
    }

    private void SetupNode(){
        try {
            settingTabPane = new FXMLLoader(getClass().getResource("SettingTabPane.fxml")).load();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}