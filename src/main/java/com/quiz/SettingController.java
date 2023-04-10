package com.quiz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;
enum SettingTab{
    QuestionTab, CategoriesTab, ExportTab, ImportTab
}

public class SettingController implements Initializable {
    @FXML
    private TabPane tabPane;
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

    public void ShowTab(SettingTab settingTab){
        switch (settingTab){
            case QuestionTab -> tabPane.getSelectionModel().select(questionTab);
            case CategoriesTab -> tabPane.getSelectionModel().select(categoriesTab);
            case ExportTab -> tabPane.getSelectionModel().select(exportTab);
            case ImportTab -> tabPane.getSelectionModel().select(importTab);
        }
    }
}
