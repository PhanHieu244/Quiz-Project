package com.quiz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingController implements Initializable {
    @FXML
    private Tab categoriesTab;
    @FXML
    private Tab questionTab;
    @FXML
    private Tab importTab;
    @FXML
    private Tab exportTab;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SetupScene();
    }


    private void SetupScene(){
        try {
            questionTab.setContent(new FXMLLoader(getClass().getResource("QuestionTab.fxml")).load());
            categoriesTab.setContent(new FXMLLoader(getClass().getResource("CategoriesTab.fxml")).load());
            importTab.setContent(new FXMLLoader(getClass().getResource("ImportTab.fxml")).load());
            exportTab.setContent(new FXMLLoader(getClass().getResource("ExportTab.fxml")).load());

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
