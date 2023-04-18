package com.DataManager;

import okhttp3.MediaType;
import org.json.simple.JSONObject;

import java.io.IOException;

public class DataManager {
    private static DataManager instance;

    public static final String url = "http://localhost:8080/api/v1/";
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    private DataManager(){ }

    public static DataManager getInstance() {
        if (instance == null){
            instance = new DataManager();
        }
        return instance;
    }



}
