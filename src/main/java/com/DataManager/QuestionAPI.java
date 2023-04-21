package com.DataManager;

import com.Question.Choice;
import com.Question.Question;
import com.Question.Test;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.*;

public class QuestionAPI {
    private final static String urlQuestion = "http://localhost:8080/api/v1/answer/quiz_id=";

    private final static String urlAnswer = "http://localhost:8080/api/v1/answer/question_id=";

    public static Map<Integer, JSONObject> getAllQuestion(int id) throws IOException {
        JSONObject jsonObject = (JSONObject) APIConnector.getData(urlQuestion + id);
        JSONArray jsonArray = (JSONArray) jsonObject.get("data");
        Map<Integer, JSONObject> map = new HashMap<>();
        for (Object json : jsonArray) {
            JSONObject jsonQues = (JSONObject) json;
            int idQues = Integer.parseInt(jsonQues.get("id").toString());
            map.put(idQues, jsonQues);
        }
        return map;
    }

    //post array question
    public static void post(String json, int id) throws IOException {
        APIConnector.postData(json, urlQuestion + id);
    }

    //put object question
    public static void put(String json, int id) throws IOException {
        APIConnector.putData(json, urlAnswer + id);
    }

    //todo get all question
    public static ArrayList<Question> getQuestionsContent(int id) throws IOException {
        Map<Integer, JSONObject> map = getAllQuestion(id);
        List<JSONObject> listJson = new ArrayList<>(map.values());
        ArrayList<Question> questions = new ArrayList<>();
        for (int i = 0; i < listJson.size(); i++) {
            JSONObject jsonObject = listJson.get(i);
            String content = jsonObject.get("description").toString();
            int quesID = Integer.parseInt(jsonObject.get("id").toString());
            questions.add(new Question(content, quesID));
        }
        return questions;
    }

    //todo get one question
    public static Question getQuestion(int quesID, int id) throws IOException {
        JSONObject jsonObject = getAllQuestion(id).get(quesID);;
        String content = jsonObject.get("description").toString();
        String base64;
        if (jsonObject.get("imgQuiz") == null) base64 = "";
        else base64 = jsonObject.get("imgQuiz").toString();
        JSONArray choicesJson = (JSONArray) jsonObject.get("questionAnswerSet");
        List<Choice> choices = getChoices(choicesJson);
        return new Question(content, base64, choices, quesID);
    }

    //todo get all choice
    private static List<Choice> getChoices(JSONArray choicesJson){
        List<Choice> choices = new ArrayList<>();
        for (int i = 0; i < choicesJson.size(); i++) {
            JSONObject object = (JSONObject) choicesJson.get(i);
            String content = object.get("description").toString();
            String base64;
            if (object.get("imgAnswer") == null) base64 = "";
            else base64 = object.get("imgAnswer").toString();
            float percent = Float.parseFloat(object.get("score").toString());
            int id = Integer.parseInt(object.get("id").toString());
            choices.add(new Choice(id, content, base64, percent));
        }
        return choices;
    }

    private static JSONObject creatJsonChoice(Choice choice){
        JSONObject jsonObject = new JSONObject();
        if (choice.id != null) jsonObject.put("id", choice.id);
        jsonObject.put("description", choice.getContentChoice());
        jsonObject.put("imgAnswer", choice.getImageDataChoice());
        jsonObject.put("score", choice.getPercentGrade());
        jsonObject.put("correct_answer", false);
        return jsonObject;
    }

    public static JSONObject creatJsonQuestion(Question question){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        List<Choice> choices = question.getChoices();
        for (int i = 0; i < choices.size(); i++) {
            jsonArray.add(creatJsonChoice(choices.get(i)));
        }
        if(question.idQuestion != null) jsonObject.put("id", question.idQuestion);
        jsonObject.put("questionAnswerDtos", jsonArray);
        jsonObject.put("description", question.getContentQuestion());
        jsonObject.put("imgQuestion", question.getImageDataQs());
        jsonObject.put("question_mark", 1);
        return jsonObject;
    }



    public static void postNewQuestion(int id, Question question){
        JSONArray json = new JSONArray();
        json.add(creatJsonQuestion(question));
        String questionString = json.toString();
        try {
            post(questionString, id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void putNewQuestion(Question question){
        String questionString = creatJsonQuestion(question).toString();
        try {
            System.out.println(questionString);
            put(questionString, question.idQuestion);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
