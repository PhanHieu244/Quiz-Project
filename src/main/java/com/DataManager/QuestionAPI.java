package com.DataManager;

import com.Question.Choice;
import com.Question.Question;
import com.Question.Quiz;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.*;

public class QuestionAPI {
    private final static String urlAllQuestions = "http://localhost:8080/api/v1/answer/quiz_id=";
    private final static String urlSubCateQuestions = "http://localhost:8080/api/v1/answer/subquiz/quiz_id=";
    private final static String urlQuestion = "http://localhost:8080/api/v1/question/";
    private final static String urlAnswer = "http://localhost:8080/api/v1/answer/question_id=";
    private final static String getQuizURL = "http://localhost:8080/api/v1/examquestion/exam_id=";
    private final static String postQuizURL = "http://localhost:8080/api/v1/examquestion/exam_id=";
    private final static String deleteQuizURL = "http://localhost:8080/api/v1/examquestion/exam_id=";
    private final static String urlPaginationCate = "http://localhost:8080/api/v1/answer/quizId=";
    /**
     * Post array question to API
     * @param json string of array json
     * @param id id of category
     */
    public static void post(String json, int id) {
        try {
            APIConnector.postData(json, urlAllQuestions + id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Put object question to API
     * @param json string of object json
     * @param id id of category
     */
    public static void put(String json, int id) {
        try {
            APIConnector.putData(json, urlAnswer + id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get all questions content in category (Do not get choice)
     * . Get description, id, name
     * @param getSubCate is get question in subcategories?
     * @param id ID of this category
     */
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

    public static ArrayList<Question> getQuestionQuiz(Quiz quiz, boolean suffer){
        String url = getQuizURL + quiz.getIdQuiz();
        ArrayList<Question> questions = new ArrayList<>();
        try{
            questions = getAllQuestionFromURL(url, suffer);
        } catch (Exception e){
            e.printStackTrace();
        }
        return questions;
    }

    public static ArrayList<Question> getQuestionQuiz(Quiz quiz){
        String url = getQuizURL + quiz.getIdQuiz();
        ArrayList<Question> questions = new ArrayList<>();
        try{
            questions = getAllQuestionFromURL(url, quiz.isSuffer());
        } catch (Exception e){
            e.printStackTrace();
        }
        return questions;
    }

    /**
     * Get all questions content in this category (get all choices)
     * @param getSubCate is get question in subcategories?
     * @param id ID of this category
     */

    public static ArrayList<Question> getAllQuestionInCate(int id, boolean getSubCate) throws IOException {
        String url = getSubCate ? urlSubCateQuestions : urlAllQuestions;
        url += id;
        /*JSONObject jsonData = (JSONObject) APIConnector.getData(url + id);
        JSONArray jsonArray = (JSONArray) jsonData.get("data");
        ArrayList<Question> questions = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject =(JSONObject) jsonArray.get(i);
            questions.add(getQuestion(jsonObject, null));
        }*/
        return getAllQuestionFromURL(url, false);
    }

    /**
     * Get all questions content in this category (get all choices)
     * @param getSubCate is get question in subcategories?
     * @param id ID of this category
     */
    public static ArrayList<Question> categoryPagination(int id, boolean getSubCate,int pageIndex, int pageSize) throws IOException {
        String url = getSubCate ? "urlPaginationSubCate" : urlPaginationCate;
        url += id + "/pageNo=" + pageIndex + "/pageSize=" + pageSize;
        return getAllQuestionFromURL(url, false);
    }


    /**
     * Get all questions content in this category (get all choices)
     * @param url URL to get question in category or quiz
     */
    public static ArrayList<Question> getAllQuestionFromURL(String url, boolean suffer) throws IOException {
        JSONObject jsonData = (JSONObject) APIConnector.getData(url);
        JSONArray jsonArray = (JSONArray) jsonData.get("data");
        ArrayList<Question> questions = new ArrayList<>();
        if (jsonArray == null) return questions;
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            questions.add(getQuestion(jsonObject, false));
        }
        return questions;
    }

    /**
     * Get a question with question ID
     * @param quesID id of this question
     */
    public static Question getQuestion(int quesID) throws IOException {
        JSONObject jsonData = (JSONObject) APIConnector.getData(urlQuestion + quesID);
        JSONObject jsonObject = (JSONObject) jsonData.get("data");
        return getQuestion(jsonObject, false);
    }

    /**
     * Get data of question with JSON object
     * @param jsonObject json object to get data
     */
    private static Question getQuestion(JSONObject jsonObject, boolean suffer){
        String content = jsonObject.get("description").toString();
        String name = (String) jsonObject.get("name");
        String base64 =(String) jsonObject.get("imgQuiz");
        int quesID = Integer.parseInt(jsonObject.get("id").toString());
        JSONArray choicesJson = (JSONArray) jsonObject.get("questionAnswerSet");
        List<Choice> choices = getChoices(choicesJson, suffer);
        return new Question(name, content, base64, choices, quesID);
    }

    //todo get all choice
    /**
     * Get all choice from question
     * @param isSuffer default: sort ID
     * @return list choices from choices JSON
     */
    private static List<Choice> getChoices(JSONArray choicesJson, boolean isSuffer){
        List<Choice> choices = new ArrayList<>();
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

    /**
     * Creat the change JSON choice from choices to put/post
     * , if null create new
     * , if not null change data of choice
     */
    private static JSONObject creatJsonChoice(Choice choice){
        JSONObject jsonObject = new JSONObject();
        if (choice.id != null) jsonObject.put("id", choice.id);
        jsonObject.put("description", choice.getContentChoice());
        jsonObject.put("imgAnswer", choice.getImageDataChoice());
        jsonObject.put("score", choice.getPercentGrade());
        jsonObject.put("correct_answer", false);
        return jsonObject;
    }

    /**
     * Creat the change JSON choice from question to put/post
     * , if null create new
     * , if not null change data of question
     */
    public static JSONObject creatJsonQuestion(Question question){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        List<Choice> choices = question.getChoices();
        for (Choice choice : choices) {
            jsonArray.add(creatJsonChoice(choice));
        }
        if(question.idQuestion != null) jsonObject.put("id", question.idQuestion);
        jsonObject.put("questionAnswerDtos", jsonArray);
        jsonObject.put("name", question.getNameQuestion());
        jsonObject.put("description", question.getContentQuestion());
        jsonObject.put("imgQuestion", question.getImageDataQs());
        jsonObject.put("question_mark", 1);
        return jsonObject;
    }


    private static JSONArray createJsonQuesQuiz(ArrayList<Question> listID){

        JSONArray jsonArray = new JSONArray();
        for (Question i : listID){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("quizQuestionId", i.getIdQuestion());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    public static void postQuesQuiz(ArrayList<Question> listID, int quizID){
        JSONArray json = createJsonQuesQuiz(listID);
        String jsonString = json.toString();
        System.out.println(jsonString);
        try {
            APIConnector.postData(jsonString, postQuizURL + quizID);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void deleteQuestion(ArrayList<Question> listID, int quizID){
        JSONArray json = createJsonQuesQuiz(listID);
        String jsonString = json.toString();
        System.out.println(jsonString);
        try {
            APIConnector.deleteData(jsonString, deleteQuizURL + quizID);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Create json array from list question
     */
    public static JSONArray creatJsonListQues(List<Question> questions){
        JSONArray json = new JSONArray();
        for (Question question : questions) {
            json.add(creatJsonQuestion(question));
        }
        return json;
    }

    /**
     * Post list questions to API
     * , post when import file
     */
    public static void postListQuestion(int id, List<Question> questions){
        String jsonString = creatJsonListQues(questions).toString();
        post(jsonString, id);
    }

    /**
     * Post a question to API
     * , use when add new question
     * @param id ID of Category to post
     */
    public static void postNewQuestion(int id, Question question){
        JSONArray json = new JSONArray();
        json.add(creatJsonQuestion(question));
        String questionString = json.toString();
        post(questionString, id);
    }

    /**
     * Put a question to API
     * , use when edit old question
     */
    public static void putNewQuestion(Question question){
        String questionString = creatJsonQuestion(question).toString();
        put(questionString, question.idQuestion);
    }

}
