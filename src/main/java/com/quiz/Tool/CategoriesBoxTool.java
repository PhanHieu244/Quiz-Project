package com.quiz.Tool;

import com.DataManager.CategoryAPI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.util.HashMap;

public class CategoriesBoxTool {
    public static HashMap<String, Integer> Setup(ComboBox<String> categoriesBox){
        try {
            ObservableList<String> categories = FXCollections.observableArrayList();
            HashMap<String, Integer> map = CategoryAPI.getMap();
            categories.addAll(map.keySet());
            categoriesBox.setItems(categories);
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
