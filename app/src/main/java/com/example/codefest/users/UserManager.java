package com.example.codefest.users;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private static UserManager instance;
    private final ArrayList<User> userList = new ArrayList<>();
    private int nextId = 1;
    private User curUser;

    private UserManager() {}

    public static synchronized UserManager getInstance() {
        if (instance == null) instance = new UserManager();
        return instance;
    }

    // ========== CRUD ==========

    public User addUser(String name, String email, String password) {
        User newUser = new User(nextId++, name, email, password);
        userList.add(newUser);
        return newUser;
    }

    public List<User> getAllUser() {
        return new ArrayList<>(userList);
    }

    public boolean deleteUser(int id) {
        return userList.removeIf(u -> u.getId() == id);
    }

    public boolean updateUser(int id, String updatedName, String updatedEmail) {
        for (User u : userList) {
            if (u.getId() == id) {
                if (updatedName != null) u.setName(updatedName);
                if (updatedEmail != null) u.setEmail(updatedEmail);
                return true;
            }
        }
        return false;
    }

    // ========== AUTH ==========

    public User loginUser(String email, String password) {
        for (User u : userList) {
            if (u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public boolean registerUser(String name, String email, String password) {
        for (User u : userList) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }
        userList.add(new User(nextId++, name, email, password));
        return true;
    }

    // ========== SESSION ==========

    public User getCurUser() {
        return curUser;
    }

    public void setCurUser(User curUser) {
        this.curUser = curUser;
    }

    public void logout() {
        this.curUser = null;
    }
}