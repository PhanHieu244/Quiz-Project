package com.quiz.Tool;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomNameGenerator {
    private static final int MAX_NAME_LENGTH = 30; // Maximum length of the generated names
    private static final int ALPHABET_SIZE = 26; // Number of letters in the alphabet
    private static Set<String> usedNames = new HashSet<>();


    public static String generateRandomName() {
        Random random = new Random();

        while (true) {
            StringBuilder nameBuilder = new StringBuilder();
            int nameLength = random.nextInt(MAX_NAME_LENGTH) + 1;

            for (int i = 0; i < nameLength; i++) {
                char randomChar = (char) (random.nextInt(ALPHABET_SIZE) + 'A');
                nameBuilder.append(randomChar);
            }

            String randomName = nameBuilder.toString();

            if (!usedNames.contains(randomName)) {
                usedNames.add(randomName);
                return randomName;
            }
        }
    }
}
