package com.quiz.Tool;

import com.DataManager.CategoryAPI;
import com.Question.Test;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.util.List;

public class CategoriesBoxTool {

    public static void Setup(ComboBox<Test> categoriesBox){
        Reset(categoriesBox);
        categoriesBox.setCellFactory(new Callback<ListView<Test>, ListCell<Test>>() {
            @Override
            public ListCell<Test> call(ListView<Test> param) {
                return new ListCell<Test>() {
                    @Override
                    protected void updateItem(Test item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(setTree(item.getGeneration()) + setTreeText(item));
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        categoriesBox.setConverter(new StringConverter<Test>() {
            @Override
            public String toString(Test test) {
                if (test == null) {
                    return "Default";
                } else {
                    return setTreeText(test);
                }
            }

            @Override
            public Test fromString(String string) {
                return null;
            }
        });
    }

    public static void Reset(ComboBox<Test> categoriesBox){
        categoriesBox.getItems().clear();
        categoriesBox.setPromptText("Default");
        getChildrenCate(categoriesBox, CategoryAPI.getAllCategories());
    }

    private static String setTree(int gen){
        return "       ".repeat(Math.max(0, gen));
    }

    private static void getChildrenCate(ComboBox<Test> categoriesBox, List<Test> categories){
        //if (categories.size() == 0) return;
        for(Test category: categories){
            categoriesBox.getItems().add(category);
            getChildrenCate(categoriesBox, category.getChildren());
        }
    }

    private static String setTreeText(Test category){
        String name = category.getNameTest();
        int total = category.getAmountQuestion();
        if (total == 0) return name;
        return name + " (" + total + ")";
    }
}
