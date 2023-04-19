package com.DataManager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.io.IOException;
import java.util.HashMap;

public class CategoryAPI {
    private final static String urlCategory = "http://localhost:8080/api/v1/quiz";


    public static JSONObject getAllCategories() throws IOException {
        return (JSONObject) APIConnector.getData(urlCategory);
    }

    public static HashMap<String, Integer> getMap() throws IOException {
        JSONObject jsonObject = getAllCategories();
        JSONArray jsonArray = (JSONArray) jsonObject.get("data");
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = (JSONObject) jsonArray.get(i);
            String name = object.get("name").toString();
            int id = Integer.parseInt(object.get("id").toString());
            map.put(name, id);
        }
        return map;
    }



    private static String creatJsonCategory(String name, String description, int cateID){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("description", description);
        jsonObject.put("category_id", cateID);
        System.out.println(jsonObject);
        return jsonObject.toString();
    }

    public static void post(String json) throws IOException {
        APIConnector.postData(json, urlCategory);
    }

    public static void postNewCategory(String name, String description, int cateID){
        String quizString = creatJsonCategory(name, description, cateID);
        try {
            post(quizString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
