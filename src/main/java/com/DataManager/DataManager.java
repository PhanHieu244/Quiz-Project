package com.DataManager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.MalformedURLException;

public class DataManager {
    private static DataManager instance;
    public static final String apiString = "http://localhost:8080/api/v1/";

    private DataManager(){ }

    public static DataManager getInstance() {
        if (instance == null){
            instance = new DataManager();
        }
        return instance;
    }

    public void GetData() throws MalformedURLException {
        APIConnector apiConnector = new APIConnector(apiString);
        JSONArray jsonArray = apiConnector.GetJSONArray("Products");
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        String name = jsonObject.get("productName").toString();
    }
}
