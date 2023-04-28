package com.quiz.QuizTab.EditQuiz;

import com.DataManager.QuestionAPI;
import com.Question.Question;
import com.Question.Test;
import com.quiz.Tool.BaseController.QuestionTabBase;
import com.quiz.Tool.CategoriesBoxTool;
import com.quiz.Tool.RandomListGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddRandomQuestionController extends QuestionTabBase {
    @FXML
    private Pagination pagination;
    @FXML
    private Button addRandomBut;
    @FXML
    private Button test;
    @FXML
    private ComboBox<Integer> randomBox;
    private int currentPage;
    private final int pageSize = 1;
    private final int maxPage = 7;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        setupPagination();
    }

    @Override
    public void Show(Test test) {
        ArrayList<Question> questionsContent;
        try {
            questionsContent = QuestionAPI.categoryPagination
                    (test.getIdTest(), showCateQues.isSelected(), currentPage, pageSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        showQuestions(questionsContent);
    }

    @Override
    protected void showQuestions(ArrayList<Question> questionsContent) {
        for (Question question: questionsContent){
            vBox.getChildren().add(setContent(question));
        }
    }

    @Override
    public void Setup() {
        CategoriesBoxTool.Setup(categoriesBox);
    }

    @Override
    protected void showList() {
        //pagination.setCurrentPageIndex(0);
        setupPagination();
    }

    public void setValueCate(Test test){
        categoriesBox.setValue(test);
    }

    public void setupPagination(){
        currentPage = 0;
        int pageCount = 1;
        if(categoriesBox.getValue() != null) {
            int amount = categoriesBox.getValue().getAmountQuestion();
            pageCount =(int) Math.ceil((double) amount / pageSize);
            randomBox.getItems().clear();
            for (int i = 0; i < amount; i++) {
                randomBox.getItems().add(i+1);
            }
        }
        pagination.setPageCount(pageCount);
        pagination.setMaxPageIndicatorCount(maxPage);
        pagination.setPageFactory(this::showPage);
    }


    private Node showPage(Integer pageIndex) {
        currentPage = pageIndex;
        vBox = new VBox();
        vBox.setPadding(new Insets(0,0,0,15));
        ScrollPane scrollPane = new ScrollPane(vBox);
        if (!showCateQues.isSelected()) showQuesList(); //todo delete if()
        return scrollPane;
    }

    @FXML
    private void addRandomQuestions(ActionEvent event) {
        if (categoriesBox.getValue() == null) return;
        List<Question> randomQuesAdd = RandomListGenerator.getRandomList(questions, randomBox.getValue());
    }
}
