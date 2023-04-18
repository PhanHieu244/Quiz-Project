package com.quiz.TabPane;

import com.DataManager.APIConnector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import okhttp3.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;


public class ImportTabController implements Initializable {
    @FXML
    public Label string;
    @FXML
    public TextField textField;
    @FXML
    private Button button;
    @FXML
    private Button get;
    @FXML
    private TextArea textArea;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
