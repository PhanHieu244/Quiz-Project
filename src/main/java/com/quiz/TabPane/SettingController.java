package com.quiz.TabPane;

import com.quiz.TabPane.QuestionTab.QuestionTabController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

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
            FXMLLoader quesLoader = new FXMLLoader(getClass().getResource("QuestionTab/QuestionTab.fxml"));
            FXMLLoader cateLoader = new FXMLLoader(getClass().getResource("CategoriesTab.fxml"));
            FXMLLoader importLoader = new FXMLLoader(getClass().getResource("ImportTab.fxml"));
            FXMLLoader exportLoader = new FXMLLoader(getClass().getResource("ExportTab.fxml"));
            questionTab.setContent(quesLoader.load());
            categoriesTab.setContent(cateLoader.load());
            importTab.setContent(importLoader.load());
            exportTab.setContent(exportLoader.load());
            QuestionTabController quesCtrl = quesLoader.getController();
            CategoriesTabController cateCtrl = cateLoader.getController();
            ImportTabController importCtrl = importLoader.getController();
            ExportTabController exportCtrl = exportLoader.getController();
            cateCtrl.quesCtrl = quesCtrl;

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
