package com.quiz.QuizTab;

import com.DataManager.QuestionAPI;
import com.Question.Question;
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
    //todo test
    @FXML
    private Button deleteBut;
    @FXML
    private Button test;
    @FXML
    private Button cancelBut;
    @FXML
    private CheckBox selectAll;
    private final float minHeight = 46f;
    private final float fontSize = 14f;
    private int count;
    private List<CheckBox> checkBoxList;
    private List<Question> questionsList;
    private List<Question> removeList;
    private List<Question> preRemoveList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkBoxList = new ArrayList<>();
        removeList = new ArrayList<>();
        preRemoveList = new ArrayList<>();
        count = 0;
        addBut.getItems().addAll("a new question", "from question bank", "a random question");
        setupPageChoose(10);
        test.setOnAction(event -> {
            CheckBox checkBox = new CheckBox();
            checkBoxList.add(checkBox);
            Label label = new Label("int i");
            Button delete = new Button("Delete");
            HBox hBox = new HBox(checkBox, label, delete);
            vBox.getChildren().add(hBox);
        });
        addBut.setOnAction(event -> {
            if (addBut.getValue() == null) return;
            String value = addBut.getValue();
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
                stage.show();
            }catch (Exception e){
                e.printStackTrace();
            }
            addBut.setValue(null);
        });
        //vBox.prefWidthProperty().bind(scroll.prefWidthProperty());
        loadData();
    }

    public void reload(){

    }

    public void addQuestion(List<Integer> idList){

    }

    private void removeQuestion(){

    }

    public void loadData(){
        try {
            questionsList = QuestionAPI.getAllQuestionInCate(1, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        showQuestions(questionsList);
    }

    private void showQuestions(List<Question> questionsContent) {
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
                removeList.remove(question);
                questionsList.remove(question);
                vBox.getChildren().remove(deleteBut.getParent());
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

    public void setupPageChoose(int numberPage){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < numberPage; i++) {
            list.add(i+1);
        }
        pageChoose.getItems().addAll(list);
    }

    @FXML
    void delete(ActionEvent event) {
        for (CheckBox checkBox: checkBoxList){
            vBox.getChildren().remove(checkBox.getParent());
        }
        removeList.addAll(preRemoveList);
        questionsList.removeAll(preRemoveList);
        preRemoveList.clear();
        setupMulti(false);
    }

    @FXML
    private void selectAllQuestion(ActionEvent event){
        boolean isSelected = selectAll.isSelected();
        preRemoveList.clear();
        for (CheckBox checkBox : checkBoxList) {
            checkBox.setSelected(isSelected);
        }
        if (isSelected){
            preRemoveList.addAll(questionsList);
        }
    }

    @FXML
    private void save(ActionEvent event) {

    }

    @FXML
    private void selectMultipleItems(ActionEvent event) {
        for (CheckBox checkBox: checkBoxList){
            preRemoveList.clear();
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

}
