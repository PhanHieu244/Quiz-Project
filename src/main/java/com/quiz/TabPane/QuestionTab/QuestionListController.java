package com.quiz.TabPane.QuestionTab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class QuestionListController implements Initializable {
    @FXML
    private VBox vBox;
    @FXML
    private CheckBox seclectAll;

    private ArrayList<CheckBox> listCheckBox = new ArrayList<CheckBox>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            for (int i = 0; i < 100; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("QuestionBox.fxml"));
                Node node = fxmlLoader.load();
                QuestionBoxController controller = fxmlLoader.getController();
                listCheckBox.add(controller.checkBox);
                controller.setText("" + i);
                vBox.getChildren().add(node);
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("loi roi");
        }

    }

    @FXML
    private void selectAllQuestion(ActionEvent event){
        boolean isSelected = seclectAll.isSelected();
        for (int i = 0; i < listCheckBox.size(); i++) {
            listCheckBox.get(i).setSelected(isSelected);
        }

    }
}
