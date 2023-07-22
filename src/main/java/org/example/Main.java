package org.example;

import java.util.List;
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
    }
}



