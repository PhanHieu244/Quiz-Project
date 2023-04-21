package com.quiz.Tool;

import com.DataManager.CategoryAPI;
import com.Question.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CategoriesBoxTool {

    public static void Setup(ComboBox<Test> categoriesBox){

        categoriesBox.getItems().addAll(CategoryAPI.getAllCategories());
        categoriesBox.setCellFactory(new Callback<ListView<Test>, ListCell<Test>>() {
            @Override
            public ListCell<Test> call(ListView<Test> param) {
                return new ListCell<Test>() {
                    @Override
                    protected void updateItem(Test item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(setTree(item.getGeneration()) + item.getNameTest()
                                    + " (" + item.getAmountQuestion() + ")");
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }

    private static String setTree(int gen){
        return " ".repeat(Math.max(0, gen));
    }

    private static void getChildrenCate(ComboBox<Test> categoriesBox, List<Test> categories){
        for(Test category: categories){
            categoriesBox.getItems().add(category);
            if (category.getChildren().size() == 0) return;
            getChildrenCate(categoriesBox, category.getChildren());
        }
    }
}
