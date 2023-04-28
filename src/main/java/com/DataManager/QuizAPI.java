package com.DataManager;

import com.Question.Quiz;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.List;

public class QuizAPI {
    private final static String examURL = "http://localhost:8080/api/v1/exam";
    private final static String postQuizURL = "http://localhost:8080/api/v1/exam";

    /**
     * post new question to database
     */
    public static void postNewQuiz(){ //todo add parameter
        String quizString = creatJsonQuiz();
        post(quizString);
    }

    public static List<Quiz> getAllQuizzes(){
        JSONArray jsonArray = getJSONQuizzes();
        //todo
        return null;
    }


    private static JSONArray getJSONQuizzes(){
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

    private static String creatJsonQuiz(){
        return null;
    }

}
