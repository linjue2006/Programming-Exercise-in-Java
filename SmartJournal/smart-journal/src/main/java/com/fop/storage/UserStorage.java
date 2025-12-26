package com.fop.storage;

import com.fop.model.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class UserStorage {
    private final Path userFile;

    public UserStorage(Path userFile) {
        this.userFile = userFile;
    }

    public Map<String, User> loadUsers() throws IOException {
        List<String> lines = Files.readAllLines(userFile, StandardCharsets.UTF_8);

        List<String> nonEmpty = new ArrayList<>();
        for (String line : lines) {
            String t = line.trim();
            if (!t.isEmpty()) nonEmpty.add(t);
        }

        if (nonEmpty.size() % 3 != 0) {
            throw new IOException("UserData.txt 格式错误：必须 3 行一组（email/name/password）。当前=" + nonEmpty.size());
        }

        Map<String, User> users = new HashMap<>();
        for (int i = 0; i < nonEmpty.size(); i += 3) {
            String email = nonEmpty.get(i);
            String name = nonEmpty.get(i + 1);
            String password = nonEmpty.get(i + 2);
            users.put(email.toLowerCase(Locale.ROOT), new User(email, name, password));
        }
        return users;
    }
public void appendUser(User u) throws IOException {
    Files.createDirectories(userFile.getParent());

    // 确保写入时和作业格式一致：Email\nDisplayName\nPassword\n
    // 并且如果文件已有内容，先补一个换行（避免黏在最后一行后面）
    String prefix = "";
    if (Files.exists(userFile) && Files.size(userFile) > 0) {
        String content = Files.readString(userFile);
        if (!content.endsWith("\n")) prefix = "\n";
    }

    String block = prefix
            + u.getEmail() + "\n"
            + u.getDisplayName() + "\n"
            + u.getPassword() + "\n";

    Files.writeString(
            userFile,
            block,
            StandardOpenOption.CREATE,
            StandardOpenOption.APPEND
    );
}








}

