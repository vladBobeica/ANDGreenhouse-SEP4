package com.example.greenhouse.repository.mock;

import com.example.greenhouse.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class MockUserRepository {
    private static final List<UserModel> users = new ArrayList<>();

    static {
        users.add(new UserModel(1, "vlad@example.com", "Vlad", "Bobeica"));
        users.add(new UserModel(2, "alin@example.com", "Alin", "Iacoban"));
        users.add(new UserModel(3, "john@example.com", "John", "Doe"));
    }

    public static List<UserModel> getUsers() {
        return users;
    }

    public static UserModel getUserById(String token) {
        for (UserModel user : users) {
            if (String.valueOf(user.getId()).equals(token)) {
                return user;
            }
        }
        return null;
    }

}
