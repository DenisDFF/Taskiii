package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static Pattern pattern;

    public static void main(String[] args) {
        String file = "file.txt";
        pattern = Pattern.compile(PhoneNumberReader.PHONE_NUMBER);

        PhoneNumberReader reader = new PhoneNumberReader();
        List<String> phoneNumbers = reader.readPhoneNumbers(file);

        for (String phoneNumber : phoneNumbers) {
            Matcher matcher = pattern.matcher(phoneNumber);
            if (matcher.matches()) {
                System.out.println(phoneNumber);
            }
        }

        String file2 = "file2.txt";
        String outputFile = "user.json";
        UserFileProcessor userFileProcessor = new UserFileProcessor();
        List<String> userLines = userFileProcessor.userFileRider(file2);
        List<User> users = userFileProcessor.createUserListFromLines(userLines);

        userFileProcessor.writeToJsonFile(users, outputFile);

        System.out.println(userLines);
        System.out.println(outputFile);

        String filePath = "words.txt";
        calculateWordFrequency(filePath);

    }
    public static void calculateWordFrequency(String filePath) {
        try {

            List<String> lines = Files.readAllLines(Paths.get(filePath));


            String content = String.join(" ", lines);


            String[] words = content.split("\\s+");


            Map<String, Integer> wordFrequency = new HashMap<>();
            for (String word : words) {
                if (!word.isEmpty()) {
                    wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                }
            }

            List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(wordFrequency.entrySet());
            sortedWords.sort((a, b) -> b.getValue().compareTo(a.getValue()));

            for (Map.Entry<String, Integer> entry : sortedWords) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
}
}



