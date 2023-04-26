package com.DataManager;

import com.Question.Choice;
import com.Question.Question;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.*;

public class QuestionAPI {
    private final static String urlAllQuestions = "http://localhost:8080/api/v1/answer/quiz_id=";
    private final static String urlSubCateQuestions = "http://localhost:8080/api/v1/answer/subquiz/quiz_id=";
    private final static String urlQuestion = "http://localhost:8080/api/v1/question/";
    private final static String urlAnswer = "http://localhost:8080/api/v1/answer/question_id=";

    //post array question
    public static void post(String json, int id) throws IOException {
        APIConnector.postData(json, urlAllQuestions + id);
    }

    //put object question
    public static void put(String json, int id) throws IOException {
        APIConnector.putData(json, urlAnswer + id);
    }

    //todo get all question
    public static ArrayList<Question> getQuestionsContent(int id, boolean getSubCate) throws IOException {
        String url = getSubCate ? urlSubCateQuestions : urlAllQuestions;
        JSONObject jsonData = (JSONObject) APIConnector.getData(url + id);
        JSONArray jsonArray = (JSONArray) jsonData.get("data");
        ArrayList<Question> questions = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject =(JSONObject) jsonArray.get(i);
            String content = jsonObject.get("description").toString();
            int quesID = Integer.parseInt(jsonObject.get("id").toString());
            String name = (String) jsonObject.get("name");
            questions.add(new Question(name, content, quesID));
        }
        return questions;
    }

    public static ArrayList<Question> getAllQuestionInCate(int id, boolean getSubCate) throws IOException {
        String url = getSubCate ? urlSubCateQuestions : urlAllQuestions;
        JSONObject jsonData = (JSONObject) APIConnector.getData(url + id);
        JSONArray jsonArray = (JSONArray) jsonData.get("data");
        ArrayList<Question> questions = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject =(JSONObject) jsonArray.get(i);
            questions.add(getQuestion(jsonObject, null));
        }
        return questions;
    }

    //todo get one question
    public static Question getQuestion(int quesID) throws IOException {
        JSONObject jsonData = (JSONObject) APIConnector.getData(urlQuestion + quesID);
        JSONObject jsonObject = (JSONObject) jsonData.get("data");
        return getQuestion(jsonObject, quesID);
    }

    private static Question getQuestion(JSONObject jsonObject, Integer quesID){
        String content = jsonObject.get("description").toString();
        String name = (String) jsonObject.get("name");
        String base64 =(String) jsonObject.get("imgQuiz");
        JSONArray choicesJson = (JSONArray) jsonObject.get("questionAnswerSet");
        List<Choice> choices = getChoices(choicesJson, false);
        if (choices.size() > 5) System.out.println("qua 5 cau hoi");
        return new Question(name, content, base64, choices, quesID);
    }

    //todo get all choice
    private static List<Choice> getChoices(JSONArray choicesJson, boolean isSuffer){
        List<Choice> choices = new ArrayList<>();
        System.out.println(choicesJson.toString());
        for (int i = 0; i < choicesJson.size(); i++) {
            JSONObject object = (JSONObject) choicesJson.get(i);
            String content = object.get("description").toString();
            String base64 =(String) object.get("imgAnswer");
            float percent = Float.parseFloat(object.get("score").toString());
            int id = Integer.parseInt(object.get("id").toString());
            choices.add(new Choice(id, content, base64, percent));
        }
        if (!isSuffer) choices.sort(new Comparator<Choice>() {
            @Override
            public int compare(Choice o1, Choice o2) {
                return Integer.compare(o1.id, o2.id);
            }
        });
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
        jsonObject.put("name", question.getNameQuestion());
        jsonObject.put("description", question.getContentQuestion());
        jsonObject.put("imgQuestion", question.getImageDataQs());
        jsonObject.put("question_mark", 1);
        return jsonObject;
    }

    public static JSONArray creatJsonListQues(List<Question> questions){
        JSONArray json = new JSONArray();
        for (Question question : questions) {
            json.add(creatJsonQuestion(question));
        }
        return json;
    }

    public static void postListQuestion(int id, List<Question> questions){
        String jsonString = creatJsonListQues(questions).toString();
        System.out.println(jsonString);
        try {
            post(jsonString, id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
            System.out.println("put: " + questionString);
            put(questionString, question.idQuestion);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
