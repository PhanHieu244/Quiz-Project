package com.DataManager;

import com.Question.Test;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryAPI {
    private final static String urlCategory = "http://localhost:8080/api/v1/quiz";


    private static JSONArray getJSONCategories() throws IOException {
        JSONObject jsonObject = (JSONObject) APIConnector.getData(urlCategory);
        return (JSONArray) jsonObject.get("data");
    }



    public static List<Test> getCateChildren(JSONArray jsonArray, int gen){
        if (jsonArray == null) return new ArrayList<>();
        List<Test> categories = new ArrayList<>();
        for (Object json: jsonArray){
            JSONObject jsonObject = (JSONObject) json;
            String name = jsonObject.get("name").toString();
            int id = Integer.parseInt(jsonObject.get("id").toString());
            int totalQuestion = Integer.parseInt(jsonObject.get("totalQuestion").toString());
            Integer idParent = null;
            if (jsonObject.get("parentID") != null) idParent = (Integer) jsonObject.get("parentID");
            JSONArray jsonChildren = (JSONArray) jsonObject.get("children");
            List<Test> children = getCateChildren(jsonChildren, gen + 1);
            categories.add(new Test(id, name, idParent, children, gen, totalQuestion));
        }
        return categories;
    }

    public static List<Test> getAllCategories(){
        try {
            return getCateChildren(getJSONCategories(), 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    private static String creatJsonCategory(String name, String description, Integer parentID){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("description", description);
        jsonObject.put("parentID", parentID);
        return jsonObject.toString();
    }

    public static void post(String json) throws IOException {
        APIConnector.postData(json, urlCategory);
    }

    public static void postNewCategory(String name, String description, Integer parentID){
        String cateString = creatJsonCategory(name, description, parentID);
        try {
            post(cateString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
