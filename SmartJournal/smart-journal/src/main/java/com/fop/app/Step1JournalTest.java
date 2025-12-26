package com.fop.app;

import com.fop.model.JournalEntry;
import com.fop.storage.JournalStorage;

import java.nio.file.Path;
import java.time.LocalDate;

public class Step1JournalTest {
    public static void main(String[] args) throws Exception {
        String email = "s100201@student.fop";
        var js = new JournalStorage(Path.of("smart-journal", "data", "journals"));

        LocalDate today = LocalDate.now();

        // 1) 保存
        JournalEntry entry = new JournalEntry(today);
        entry.setWeather("Cloudy");
        entry.setMood("POSITIVE");
        entry.setText("Hello journal! 今天我把工程结构搭起来了。");

        js.saveEntry(email, entry);
        System.out.println("Saved: " + today);

        // 2) 读取
        var loaded = js.loadEntry(email, today);
        System.out.println("Loaded present? " + loaded.isPresent());
        loaded.ifPresent(e -> {
            System.out.println("date=" + e.getDate());
            System.out.println("weather=" + e.getWeather());
            System.out.println("mood=" + e.getMood());
            System.out.println("text=" + e.getText());
        });
    }
}

