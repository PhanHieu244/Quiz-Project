package com.quiz.Tool;

import com.DataManager.CategoryAPI;
import com.Question.Test;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CategoriesBoxTool {
    private static List<ComboBox<Test>> cateBoxList = new ArrayList<>();

    public static void Setup(ComboBox<Test> categoriesBox){
        Reset(categoriesBox);
        cateBoxList.add(categoriesBox);
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

    private static void Reset(ComboBox<Test> categoriesBox){
        categoriesBox.getItems().clear();
        categoriesBox.setPromptText("Default");
        getChildrenCate(categoriesBox, CategoryAPI.getAllCategories());
    }

    public static void resetAll(){
        for (ComboBox<Test> testComboBox : cateBoxList) {
            Reset(testComboBox);
        }
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

    public static int getNewCate(){
        int maxID = -1;
        for (ComboBox<Test> comboBox : cateBoxList) {
            Test test = comboBox.getValue();
            if (test != null && test.getIdTest() > maxID) {
                maxID = test.getIdTest();
            }
        }
        System.out.println(maxID);
        return maxID;
    }

    public static int randomCate(ComboBox<Test> categoriesBox){
        Random random = new Random();

        int randomIndex = random.nextInt(categoriesBox.getItems().size());
        Test randomComboBox = categoriesBox.getItems().get(randomIndex);
        return randomComboBox.getIdTest();
    }
}
