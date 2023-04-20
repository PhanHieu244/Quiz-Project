package com.DataManager;

import com.Question.Test;

import java.util.List;

public class CategoriesData {
    private static List<Test> categories;

    protected static void setData(List<Test> list){
        categories = list;
    }

    public static String getCateFromID(int id){
        for (Test category :
                categories) {
            if (category.getIdTest() == id) return category.getNameTest();
        }
        System.out.println("ID is not found");
        return null;
    }
}
