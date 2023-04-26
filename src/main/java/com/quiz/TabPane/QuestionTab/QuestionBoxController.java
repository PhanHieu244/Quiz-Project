package com.quiz.TabPane.QuestionTab;

import com.quiz.AddQuestion.EditQuestionController;
import com.quiz.MainUI.UIController;
import com.quiz.Tool.BaseController.QuestionBoxBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;

import java.io.IOException;


public class QuestionBoxController extends QuestionBoxBase {
    @FXML
    protected Hyperlink edit;

    @FXML
    private void editQuestion(ActionEvent event){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/quiz/AddQuestion/EditQuestion.fxml"));
            Node node = fxmlLoader.load();
            EditQuestionController controller = fxmlLoader.getController();
            UIController.Instance.SetCenter(node);
            controller.loadData(quesID);
            controller.setup(category);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
