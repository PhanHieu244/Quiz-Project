package com.quiz.QuizTab.EditQuiz;

import com.Question.Question;
import com.Question.Test;
import com.quiz.Tool.BaseController.QuestionTabBase;
import com.quiz.Tool.CategoriesBoxTool;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddRandomQuestionController extends QuestionTabBase {
    @FXML
    private Pagination pagination;
    @FXML
    private Button test;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupPagination(7,30);
        super.initialize(url, resourceBundle);
    }

    @Override
    protected void showQuestions(ArrayList<Question> questionsContent) {
        for (Question question: questionsContent){
            Label label = new Label(question.getContentQuestion()); //todo name question
            label.setPrefSize(725, 46);
            label.setMinHeight(46);
            label.setFont(Font.font(14));
            vBox.getChildren().add(label);
        }
    }

    @Override
    public void Setup() {
        CategoriesBoxTool.Setup(categoriesBox);
    }

    @Override
    protected void showList() {
        //pagination.setCurrentPageIndex(0);
        setupPagination(4,10);
    }

    public void setValueCate(Test test){
        categoriesBox.setValue(test);
    }

    public void setupPagination(int maxPage, int pageCount){
        pagination.setPageCount(pageCount);
        pagination.setMaxPageIndicatorCount(maxPage);
        pagination.setPageFactory(this::showPage);
    }

    private void loadPage(int numPage){

    }

    private Node showPage(Integer pageIndex) {
        vBox = new VBox();
        ScrollPane scrollPane = new ScrollPane(vBox);
        showQuesList();
        for (int i = 0; i < 100; i++) {
            vBox.getChildren().add(new Label(pageIndex + ""));
        }
        return scrollPane;
    }
}
