package com.quiz;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingPopUpController implements Initializable {
    @FXML
    private Hyperlink questionLink;
    @FXML
    private Hyperlink categoriesLink;
    @FXML
    private Hyperlink exportLink;
    @FXML
    private Hyperlink importLink;

    public UIController mainController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        questionLink.setOnAction(e -> OpenTab(SettingTab.QuestionTab));
        categoriesLink.setOnAction(e -> OpenTab(SettingTab.CategoriesTab));
        exportLink.setOnAction(e -> OpenTab(SettingTab.ExportTab));
        importLink.setOnAction(e -> OpenTab(SettingTab.ImportTab));
    }

    private void OpenTab(SettingTab settingTab){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SettingTabPane.fxml"));
            Parent root = fxmlLoader.load();
            SettingController settingController = fxmlLoader.getController();
            mainController.SetCenter(root);
            settingController.ShowTab(settingTab);
            mainController.settingBut.setVisible(false);
            Stage popup = (Stage) questionLink.getScene().getWindow();
            popup.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
