package com.DataManager;

import com.Question.Quiz;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuizAPI {
    private final static String examURL = "http://localhost:8080/api/v1/exam";
    private final static String postQuizURL = "http://localhost:8080/api/v1/exam";
    private final static String putQuizURL = "http://localhost:8080/api/v1/exam/";


    /**
     * post new question to database
     */
    public static void postNewQuiz(String name, Integer timeLimit, boolean suffer){
        JSONObject json = creatJsonQuiz(name, timeLimit, 10,suffer);
        post(json.toString());
    }

    public static ArrayList<Quiz> getAllQuizzes(){
        try {
            JSONArray jsonArray = getJSONQuizzes();
            if (jsonArray.size() == 0) return new ArrayList<>();
            ArrayList<Quiz> quizzes = new ArrayList<>();
            for (Object json: jsonArray){
                JSONObject jsonObject = (JSONObject) json;
                String name = jsonObject.get("name").toString();
                int id = Integer.parseInt(jsonObject.get("id").toString());
                int minutes = Integer.parseInt(jsonObject.get("timeLimit").toString());
                float grade = Float.parseFloat(jsonObject.get("grade").toString());
                quizzes.add(new Quiz(id, name, minutes, grade));
            }
            return quizzes;
        } catch (IOException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    private static JSONArray getJSONQuizzes() throws IOException{
        JSONObject jsonObject = (JSONObject) APIConnector.getData(examURL);
        return (JSONArray) jsonObject.get("data");
    }
    /**
     * Post new quiz to API
     * @param json string of object json
     */
    private static void post(String json) {
        try {
            APIConnector.postData(json,postQuizURL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void putInfoQuiz(Quiz quiz){
        String name = quiz.getName();
        int timeLimit = quiz.getMinutes();
        float grade = quiz.getGrade();
        boolean suffer = quiz.isSuffer();
        JSONObject jsonObject = creatJsonQuiz(name, timeLimit, grade, suffer);
        jsonObject.put("id", quiz.getIdQuiz());
        String quizString = jsonObject.toString();
        try{
            APIConnector.putData(quizString,putQuizURL + quiz.getIdQuiz());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static JSONObject creatJsonQuiz(String name, Integer timeLimit, float grade, boolean suffer){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("description","");
        jsonObject.put("name", name);
        jsonObject.put("timeLimit", timeLimit);
        jsonObject.put("suffer", suffer);
        jsonObject.put("grade", grade);
        return jsonObject;
    }

}
