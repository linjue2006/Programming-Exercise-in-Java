package com.fop.service;
import com.fop.storage.UserStorage; 
import com.fop.model.User;

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
        User u = usersByEmailLower.get(email.toLowerCase());
        if (u == null) return null;
        if (!u.getPassword().equals(password)) return null;
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

    User u = new User(email, name, password);

    // 写入 UserData.txt（你刚写好的 appendUser）
    userStorage.appendUser(u);

    // 更新内存 Map，让注册后立刻能登录
    usersByEmailLower.put(key, u);

    return u;
}




}
