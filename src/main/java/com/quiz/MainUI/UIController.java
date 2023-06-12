package com.quiz.MainUI;
import com.Question.Quiz;
import com.quiz.QuizTab.PreviousAttemptsController;
import com.quiz.TabPane.SettingController;
import com.quiz.TabPane.SettingTab;
import com.quiz.Tool.AlertTool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UIController implements Initializable {

    public static UIController Instance;
    @FXML
    private BorderPane main;
    @FXML
    private Node addQuestion;
    @FXML
    public Button settingBut;
    @FXML
    private HBox box;
    @FXML
    private Hyperlink listQuiz;

    private MainTab mainTab;

    private Quiz quiz;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Instance == null){
            Instance = this;
        }
        setMainUI(MainTab.QuizList);

        listQuiz.setOnAction(event -> {
            if (mainTab == MainTab.Preview) return;
            mainTab = MainTab.QuizList;
            setMainUI(mainTab);
            removeElements(listQuiz, false);
        });

    }

    public void setMainUI(MainTab mainTab){
        String source = "";
        switch (mainTab){
            case QuizList -> {
                source = "/com/quiz/QuizTab/QuizList.fxml";
                this.mainTab = mainTab;
                settingBut.setVisible(true);
            }
            case AddNewQuiz -> source = "/com/quiz/QuizTab/AddNewQuiz.fxml";
            default -> AlertTool.showWarning("Main UI error");
        }
        settingBut.setVisible(mainTab == MainTab.QuizList);
        try {
            Node quizList = new FXMLLoader(getClass().getResource(source)).load();
            main.setCenter(quizList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    private void ShowSettingPopUp(ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/quiz/TabPane/SettingPopUp.fxml"));
            Parent root = fxmlLoader.load();
            //SettingPopUpController settingPopUpController = fxmlLoader.getController();
            //settingPopUpController.mainController = this;
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(settingBut.getScene().getWindow());
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void SetCenter(Node node){
        main.setCenter(node);
    }

    public void SetFullSize(){
        ((Stage) settingBut.getScene().getWindow()).setMaximized(true);
    }

    public void openTabPane(SettingTab settingTab){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/quiz/TabPane/SettingTabPane.fxml"));
            Parent root = fxmlLoader.load();
            SettingController settingController = fxmlLoader.getController();
            SetCenter(root);
            settingController.ShowTab(settingTab);
            settingBut.setVisible(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addSlash(){
        Label slash = new Label("/");
        slash.setPrefSize(11, 32);
        slash.setMaxSize(11, 32);
        slash.setMinSize(11, 32);
        slash.setFont(Font.font(13));
        box.getChildren().add(slash);
    }

    private Labeled addNewLink(String name){
        addSlash();
        Labeled link = new Hyperlink(name);
        link.setFont(Font.font(14));
        link.setTextAlignment(TextAlignment.CENTER);
        link.setStyle("-fx-text-fill: #e70808;");
        link.setPadding(new Insets(5, 5, 0, 3));
        box.getChildren().add(link);
        return link;
    }

    private void removeElements(Object object, boolean include){
        int inc = include ? -1 : 1;
        int start = box.getChildren().indexOf(object) + inc;
        int end = box.getChildren().size();
        box.getChildren().remove(start, end);
    }

    public void openEditingQues(){
        mainTab = MainTab.EditQues;
        Hyperlink quesBank = (Hyperlink) addNewLink("Question bank");
        quesBank.setOnAction(event -> {
            openTabPane(SettingTab.QuestionTab);
            removeElements(quesBank, true);
        });
        Hyperlink questions = (Hyperlink) addNewLink("Questions");
        Hyperlink edit = (Hyperlink) addNewLink("Editing multiple choice question");
    }

    public void openAttempt(Quiz quiz){
        settingBut.setVisible(false);
        if(mainTab == MainTab.EditQuiz){
            int size = box.getChildren().size();
            box.getChildren().remove( size - 2, size);
            mainTab = MainTab.Attempt;
            return;
        }
        this.quiz = quiz;
        mainTab = MainTab.Attempt;
        Hyperlink general = (Hyperlink) addNewLink("General");
        Hyperlink attempt = (Hyperlink) addNewLink(quiz.getName());
        attempt.setOnAction(event -> {
            try{
                if (mainTab == MainTab.Attempt || mainTab == MainTab.Preview){
                    return;
                }
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/quiz/QuizTab/PreviousAttempts.fxml"));
                Parent root = fxmlLoader.load();
                PreviousAttemptsController controller = fxmlLoader.getController();
                controller.Setup(quiz);
                SetCenter(root);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void OpenEditQuiz(){
        mainTab = MainTab.EditQuiz;
        Hyperlink edit = (Hyperlink) addNewLink("EditQuiz");
    }

    public void openPreview(){
        mainTab = MainTab.Preview;
        Hyperlink preview = (Hyperlink) addNewLink("Preview");
    }

    public void removeBox(){
        removeElements(listQuiz, false);
    }

    /*public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }*/
}