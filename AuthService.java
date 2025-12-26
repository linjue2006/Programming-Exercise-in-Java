package com.fop.service;

import com.fop.model.User;
import com.fop.storage.UserStorage;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Map;

public class AuthService {

    private final Map<String, User> usersByEmailLower;
    private final UserStorage userStorage;

    public AuthService(Map<String, User> usersByEmailLower, UserStorage userStorage) {
        this.usersByEmailLower = usersByEmailLower;
        this.userStorage = userStorage;
    }

    public User login(String email, String password) {
        if (email == null) return null;
        
        // 1. 先把用户输入的密码进行加密
        String hashedInput = hashPassword(password);
        
        User u = usersByEmailLower.get(email.toLowerCase());
        if (u == null) return null;

        // 2. 比较：加密后的输入 vs 数据库里存的加密密码
        if (!u.getPassword().equals(hashedInput)) return null;
        
        return u;
    }

    public User register(String email, String name, String password) throws Exception {
        if (email == null) throw new IllegalArgumentException("Email cannot be null");
        if (name == null) throw new IllegalArgumentException("Name cannot be null");
        if (password == null) throw new IllegalArgumentException("Password cannot be null");

        email = email.trim();
        name = name.trim();

        if (email.isEmpty() || name.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Email/Name/Password cannot be empty");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }

        String key = email.toLowerCase();
        if (usersByEmailLower.containsKey(key)) {
            throw new IllegalArgumentException("Email already registered");
        }

        // 3. 注册时：先加密密码，再存进 User 对象
        String hashedPassword = hashPassword(password);
        User u = new User(email, name, hashedPassword);

        userStorage.appendUser(u);
        usersByEmailLower.put(key, u);

        return u;
    }

    /**
     * 辅助方法：使用 SHA-256 算法加密密码
     */
    private String hashPassword(String plainPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(plainPassword.getBytes(StandardCharsets.UTF_8));
            
            // 将 byte 数组转为 16 进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Hashing failed", e);
        }
    }
}
