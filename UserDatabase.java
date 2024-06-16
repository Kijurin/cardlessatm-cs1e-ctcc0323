package com.totallyrealbanking;

import java.util.HashMap;
import java.util.Map;

public class UserDatabase {
    private static Map<String, User> usersByEmail = new HashMap<>();
    private static Map<String, User> usersByPhone = new HashMap<>();

    public static void addUser(String email, String phone, String password) {
        User user = new User(email, phone, password);
        usersByEmail.put(email, user);
        usersByPhone.put(phone, user);
    }

    public static boolean validateUser(String identifier, String password) {
        User user = usersByEmail.get(identifier);
        if (user == null) {
            user = usersByPhone.get(identifier);
        }
        return user != null && user.getPassword().equals(password);
    }

    public static double getBalance(String identifier) {
        User user = usersByEmail.get(identifier);
        if (user == null) {
            user = usersByPhone.get(identifier);
        }
        return user != null ? user.getBalance() : 0.0;
    }

    public static boolean withdraw(String identifier, double amount) {
        User user = usersByEmail.get(identifier);
        if (user == null) {
            user = usersByPhone.get(identifier);
        }
        if (user != null && user.getBalance() >= amount) {
            user.setBalance(user.getBalance() - amount);
            return true;
        }
        return false;
    }

    public static void deposit(String identifier, double amount) {
        User user = usersByEmail.get(identifier);
        if (user == null) {
            user = usersByPhone.get(identifier);
        }
        if (user != null) {
            user.setBalance(user.getBalance() + amount);
        }
    }
}

class User {
    private String email;
    private String phone;
    private String password;
    private double balance;

    public User(String email, String phone, String password) {
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.balance = 0.0;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}