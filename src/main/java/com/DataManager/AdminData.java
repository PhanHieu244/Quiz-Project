package com.DataManager;

import org.json.simple.JSONObject;

import java.io.IOException;

public class AdminData {
    public CategoryAPI categoryAPI = new CategoryAPI(DataManager.url);
    public JSONObject categoryJson;

    public void Init(){
        try {
            categoryJson = categoryAPI.getAllCatagories();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
