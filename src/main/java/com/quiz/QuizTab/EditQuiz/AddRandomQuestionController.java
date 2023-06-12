package com.quiz.QuizTab.EditQuiz;

import com.DataManager.QuestionAPI;
import com.Question.Question;
import com.Question.Test;
import com.quiz.Tool.AlertTool;
import com.quiz.Tool.BaseController.QuestionAddTab;
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

public class AddRandomQuestionController extends QuestionAddTab {
    @FXML
    private Pagination pagination;
    @FXML
    private Button addRandomBut;
    @FXML
    private Button test;
    @FXML
    private ComboBox<Integer> randomBox;
    private int sizeQuestion = 0;
    private int currentPage;
    private final int pageSize = 15;
    private final int maxPage = 7;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        setupPagination();
    }

    @Override
    public void Show(Test test) {
        int id = test.getIdTest();
        if (showCateQues.isSelected()) showWithSubCate(id);
        else showOnlyCate(id);
        showQuestions(questions);
    }

    private void showOnlyCate(int id){
        try {
            questions = QuestionAPI.categoryPagination
                    (id, false, currentPage, pageSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showWithSubCate(int id){
        try {
            questions.clear();
            ArrayList<Question> listQues = QuestionAPI.getAllQuestionInCate(id, true);
            sizeQuestion = listQues.size();
            int index = currentPage;
            int start = pageSize * index;
            int end = Math.min(pageSize * (index + 1), listQues.size()) ;
            if(start > end) {
                System.out.println("error");
                return;
            }
            questions.addAll(listQues.subList(start, end));

        } catch (Exception e){
            e.printStackTrace();
        }
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
        pagination.setPageFactory(this::showPage);
        currentPage = 0;
        int pageCount = 1;
        if(categoriesBox.getValue() != null) {
            int amount = showCateQues.isSelected() ? sizeQuestion : categoriesBox.getValue().getAmountQuestion();
            pageCount =(int) Math.ceil((double) amount / pageSize);
            randomBox.getItems().clear();
            for (int i = 0; i < amount; i++) {
                randomBox.getItems().add(i+1);
            }
        }
        pagination.setPageCount(pageCount);
        pagination.setMaxPageIndicatorCount(maxPage);
    }


    private Node showPage(Integer pageIndex) {
        currentPage = pageIndex;
        vBox = new VBox();
        vBox.setPadding(new Insets(0,0,0,15));
        ScrollPane scrollPane = new ScrollPane(vBox);
        showQuesList();
        return scrollPane;
    }

    @FXML
    private void addRandomQuestions(ActionEvent event) {
        if (categoriesBox.getValue() == null){
            AlertTool.showWarning("Category not found");
            return;
        }
        if (randomBox.getValue() == null){
            AlertTool.showWarning("Number random not found");
            return;
        }
        List<Question> parent;
        try {
            parent = QuestionAPI.getAllQuestionInCate(categoriesBox.getValue().getIdTest(), showCateQues.isSelected());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Question> randomQuesAdd = RandomListGenerator.getRandomList(parent, randomBox.getValue());
        close(randomQuesAdd);
    }
}
