package com.quiz.TabPane;

import com.DataManager.APIConnector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class ImportTabController implements Initializable {
    @FXML
    public Label string;

    public static final String apiString = "http://localhost:8080/api/v1/";


    public void GetData() throws MalformedURLException {
        APIConnector apiConnector = new APIConnector(apiString);
        JSONArray jsonArray = apiConnector.GetJSONArray("Products");
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        String name = jsonObject.get("productName").toString();
        string.setText(name);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*try {
            GetData();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }*/
    }
}
