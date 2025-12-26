package com.fop.app;

import com.fop.storage.UserStorage;

import java.nio.file.Path;

public class Step1Test {
    public static void main(String[] args) throws Exception {
        var us = new UserStorage(Path.of("smart-journal", "data", "UserData.txt"));

        var users = us.loadUsers();

        System.out.println("Loaded users = " + users.size());
        users.values().forEach(System.out::println);
    }
}
