package com.DataManager;

import org.json.simple.JSONObject;


import java.io.IOException;

public class CategoryAPI {
    private final String urlCatagory;

    private final String query = "quiz";

    public CategoryAPI(String url) {
        urlCatagory = url + query;
    }

    public JSONObject getAllCatagories() throws IOException {
        return (JSONObject) APIConnector.getData(urlCatagory);
    }

    public String creatJsonCatagory(String name, String description, int cateID){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("description", description);
        jsonObject.put("category_id", cateID);
        return jsonObject.toString();
    }

    public void post(String json) throws IOException {
        APIConnector.postData(json, urlCatagory);
    }

    public void postNewCategory(String name, String description, int cateID){
        String quizString = creatJsonCatagory(name, description, cateID);
        try {
            post(quizString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
