package com.quiz.Tool;

import com.Question.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomListGenerator {

    public static List<Question> getRandomList(List<Question> parentList, int numElements) {
        List<Question> randomList = new ArrayList<>();
        Random rand = new Random();

        // Nếu số phần tử cần lấy vượt quá số phần tử trong danh sách cha,
        // thì chỉ lấy số phần tử tối đa có trong danh sách cha
        int maxNumElements = Math.min(numElements, parentList.size());

        while (randomList.size() < maxNumElements) {
            int randomIndex = rand.nextInt(parentList.size());
            Question randomElement = parentList.get(randomIndex);

            if (!randomList.contains(randomElement)) {
                randomList.add(randomElement);
            }
        }

        return randomList;
    }
}