package com.quiz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField adminEmail;
    @FXML
    private PasswordField adminPassword;
    @FXML
    private TextField studentEmail;
    @FXML
    private PasswordField studentPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Hello");
    }

    @FXML
    private void SwitchAdminHomeScene(){
        System.out.println("changeScene");
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Admin_UI.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) adminEmail.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (Exception e){
            e.printStackTrace();;
        }
    }


}
