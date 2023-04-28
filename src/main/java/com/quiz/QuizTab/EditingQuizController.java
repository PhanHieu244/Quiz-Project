package com.quiz.QuizTab;

import com.DataManager.QuestionAPI;
import com.Question.Question;
import com.quiz.Tool.BaseController.QuestionAddTab;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class EditingQuizController implements Initializable {
    @FXML
    private ComboBox<String> addBut;
    @FXML
    private TextField maxGradeField;
    @FXML
    private Label questionLabel;
    @FXML
    private Label quizName;
    @FXML
    private Label mark;
    @FXML
    private Button saveBut;
    @FXML
    private Button selectMultiBut;
    @FXML
    private CheckBox sufferBox;
    @FXML
    private VBox vBox;
    @FXML
    private ScrollPane scroll;
    @FXML
    private ComboBox<Integer> pageChoose;
    @FXML
    private Button deleteBut;
    @FXML
    private Button test;
    @FXML
    private Button cancelBut;
    @FXML
    private CheckBox selectAll;
    private List<CheckBox> checkBoxList;
    private List<Question> questionsList;
    private List<Question> removeList;
    private List<Question> preRemoveList;
    private List<Question> addList;
    private final float minHeight = 46f;
    private final float fontSize = 14f;
    private final int quesInPage = 4;
    private int count;
    private int currentPage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkBoxList = new ArrayList<>();
        removeList = new ArrayList<>();
        preRemoveList = new ArrayList<>();
        addList = new ArrayList<>();
        count = 0;

        addBut.getItems().addAll("a new question", "from question bank", "a random question");
        test.setOnAction(event -> {
            System.out.println(removeList.size());
            System.out.println(questionsList.size());
            System.out.println(addList.size());
        });

        addBut.setOnAction(event -> {
            if (addBut.getValue() == null) return;
            String value = addBut.getValue();
            addBut.setValue(null); //todo error
            FXMLLoader loader;
            if (value.equals("a random question")){
                loader = new FXMLLoader(getClass().getResource("/com/quiz/QuizTab/EditQuiz/AddRandomQuestion.fxml"));
            }
            else if (value.equals("from question bank")){
                loader = new FXMLLoader(getClass().getResource("/com/quiz/QuizTab/EditQuiz/ShowBankQuestion.fxml"));
            }else {
                return;
            }
            try{
                Scene scene = new Scene(loader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(addBut.getScene().getWindow());
                QuestionAddTab questionAddTab = loader.getController();
                questionAddTab.setEditController(this);
                stage.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        pageChoose.setOnAction(event -> {
            if (pageChoose.getValue() == null) return;
            checkBoxList.clear();
            preRemoveList.clear();
            showQuesInPage(pageChoose.getValue() - 1);
            setupMulti(false);
        });
        //vBox.prefWidthProperty().bind(scroll.prefWidthProperty());
        loadData(); //todo delete

    }

    public void addQuestion(List<Question> listAdd){
        questionsList.addAll(listAdd);
        addList.addAll(listAdd);
        vBox.getChildren().clear();
        setupPageChoose();
        showQuesInPage(currentPage);
        setLabel();
    }

    public void loadData(){
        try {
            questionsList = QuestionAPI.getAllQuestionInCate(1, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setupPageChoose();
        showQuesInPage(0);
        setLabel();
    }

    private int getPageNum(){
        return (int) Math.ceil((double) questionsList.size() / quesInPage);
    }

    private void showQuestions(List<Question> questionsContent) {
        checkBoxList.clear();
        for (Question question: questionsContent){
            //set up check box to pre delete
            CheckBox checkBox = new CheckBox();
            checkBox.setOnAction(event -> {
                if (checkBox.isSelected()) preRemoveList.add(question);
                else preRemoveList.remove(question);
            });
            checkBoxList.add(checkBox);
            checkBox.setMinHeight(minHeight);
            checkBox.setVisible(false);
            //set up content label
            Label label = new Label
                    (question.getNameQuestion() + ": " + question.getContentQuestion());
            label.setPrefSize(725, minHeight);
            label.setMinHeight(minHeight);
            label.setFont(Font.font(fontSize));
            //set up delete button
            Button deleteBut = new Button("Delete");
            deleteBut.setPrefSize(52, 25);
            deleteBut.setTextFill(Color.WHITE);
            deleteBut.setStyle("-fx-background-color: #2ba5eb;");
            HBox.setMargin(deleteBut, new Insets(0, 0 ,0, 200));
            deleteBut.setOnAction(event -> {
                removeList.add(question);
                questionsList.remove(question);
                vBox.getChildren().remove(deleteBut.getParent());
                setupPageChoose(); //todo check
                setLabel();
            });
            //add node to HBox
            HBox hBox = new HBox(checkBox, label, deleteBut);
            hBox.setSpacing(15);
            hBox.setPrefWidth(minHeight);
            hBox.setMinHeight(minHeight);
            hBox.setAlignment(Pos.CENTER_LEFT);
            if (count++ % 2 == 0) hBox.setStyle("-fx-background-color: #e4e3e3;");
            vBox.getChildren().add(hBox);
        }
    }

    @FXML
    void delete(ActionEvent event) {
        for (CheckBox checkBox: checkBoxList){
            if (checkBox.isSelected()) vBox.getChildren().remove(checkBox.getParent());
            else checkBox.setVisible(false);
        }
        removeList.addAll(preRemoveList);
        questionsList.removeAll(preRemoveList);
        preRemoveList.clear();
        setupMulti(false);
        setupPageChoose();
        showQuesInPage(currentPage - 1);
        setLabel();
    }

    @FXML
    private void selectAllQuestion(ActionEvent event){
        boolean isSelected = selectAll.isSelected();
        preRemoveList.clear();
        for (CheckBox checkBox : checkBoxList) {
            checkBox.setSelected(isSelected);
        }
        if (isSelected){
            preRemoveList.addAll(getSubList());
        }
    }

    @FXML
    private void save(ActionEvent event) {

    }

    @FXML
    private void selectMultipleItems(ActionEvent event) {
        for (CheckBox checkBox: checkBoxList){
            checkBox.setVisible(true);
            checkBox.setSelected(false);
        }
        setupMulti(true);
    }

    @FXML
    private void cancelSelectMulti(ActionEvent event){
        for (CheckBox checkBox: checkBoxList){
            checkBox.setVisible(false);
        }
        preRemoveList.clear();
        setupMulti(false);
    }

    @FXML
    private void suffer(ActionEvent event) {

    }

    private void setupMulti(boolean show){
        selectAll.setSelected(false);
        selectAll.setVisible(show);
        deleteBut.setVisible(show);
        cancelBut.setVisible(show);
    }

    private List<Question> getSubList(){
        if (questionsList.isEmpty()) return new ArrayList<>();
        int index = currentPage - 1;
        int start = quesInPage * index;
        int end =Math.min(quesInPage * (index + 1), questionsList.size()) ;
        return questionsList.subList(start, end);
    }

    private void showQuesInPage(int index){
        if (questionsList.isEmpty()) return;
        currentPage = index + 1;
        vBox.getChildren().clear();
        int start = quesInPage * index;
        int end =Math.min(quesInPage * (index + 1), questionsList.size()) ;
        showQuestions(questionsList.subList(start, end));
    }

    private void setupPageChoose(){
        pageChoose.getItems().clear();
        List<Integer> list = new ArrayList<>();
        int num = getPageNum();
        if (num == 0) return;
        for (int i = 0; i < num; i++) {
            list.add(i+1);
        }
        pageChoose.getItems().addAll(list);
        if (currentPage > num || currentPage < 1) currentPage = 1;
        pageChoose.setValue(currentPage);
    }

    private void setLabel(){
        questionLabel.setText("Question: "+ questionsList.size() +" | This quiz is open");
        mark.setText("Total of marks: "+ questionsList.size() + ".00");
    }
}
