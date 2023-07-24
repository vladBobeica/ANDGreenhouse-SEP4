package com.example.greenhouse.model.mock;

import java.util.ArrayList;
import java.util.List;

public class MockUserData {
    private static final List<User> users = new ArrayList<>();

    static {
        users.add(new User("one@gmail.com", "111"));
        users.add(new User("vlad@gmail.com", "1111"));
        users.add(new User("alin@gmail.com", "1234"));
    }

    public static List<User> getUsers() {
        return users;
    }

    public static class User {
        private String email;
        private String password;

        public User(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }
}
