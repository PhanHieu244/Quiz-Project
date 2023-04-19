package com.DataManager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.io.IOException;

public class CategoryAPI {
    private final static String urlCatagory = "http://localhost:8080/api/v1/quiz";

    private final String query = "quiz";


    public static JSONObject getAllCatagories() throws IOException {
        return (JSONObject) APIConnector.getData(urlCatagory);
    }

    public static String[] getName() throws IOException {
        JSONObject jsonObject = getAllCatagories();
        JSONArray jsonArray = (JSONArray) jsonObject.get("data");
        String[] names = new String[jsonArray.size()];
        for (int i = 0; i < names.length; i++) {
            JSONObject object = (JSONObject) jsonArray.get(i);
            names[i] = object.get("name").toString();
        }
        return names;
    }

    public static String creatJsonCatagory(String name, String description, int cateID){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("name", name);
        jsonObject.put("description", description);
        jsonObject.put("category_id", cateID);
        return jsonObject.toString();
    }

    public static void post(String json) throws IOException {
        APIConnector.postData(json, urlCatagory);
    }

    public static void postNewCategory(String name, String description, int cateID){
        String quizString = creatJsonCatagory(name, description, cateID);
        try {
            post(quizString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
