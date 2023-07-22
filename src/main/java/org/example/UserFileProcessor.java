package org.example;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserFileProcessor {

    public List<String> userFileRider(String file2) {
        try {
            return Files.readAllLines(Paths.get(file2));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> createUserListFromLines(List<String> userLines) {
        List<User> users = new ArrayList<>();

        for (String userLine : userLines) {
            String[] parts = userLine.split(" ");
            if (parts.length == 2) {
                String name = parts[0];
                try {
                    int age = Integer.parseInt(parts[1]);
                    User user = new User(name, age);
                    users.add(user);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid age for user: " + name + " " + parts[1]);
                }
            }
        }

        return users;
    }

    public void writeToJsonFile(List<User> users, String outputFile) {
        Gson gson = new Gson();
        String json = gson.toJson(users);

        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class User {
    private String name;
    private int age;

    // Constructor
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

//    public void writeToJsonFile(List<String> users, String outputFile) {
//        Gson gson = new Gson();
//        String json = gson.toJson(users);
//
//        try (FileWriter writer = new FileWriter(outputFile)) {
//            writer.write(json);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }