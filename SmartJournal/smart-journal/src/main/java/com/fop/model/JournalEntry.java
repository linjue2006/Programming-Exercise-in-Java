package com.fop.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class JournalEntry {
    private final LocalDate date;
    private String text;
    private String weather; // e.g. "Cloudy"
    private String mood;    // e.g. "POSITIVE"/"NEGATIVE"
    private LocalDateTime lastUpdated;

    public JournalEntry(LocalDate date) {
        this.date = date;
        this.text = "";
        this.weather = "";
        this.mood = "";
        this.lastUpdated = LocalDateTime.now();
    }

    public LocalDate getDate() { return date; }
    public String getText() { return text; }
    public String getWeather() { return weather; }
    public String getMood() { return mood; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }

    public void setText(String text) { this.text = text; touch(); }
    public void setWeather(String weather) { this.weather = weather; touch(); }
    public void setMood(String mood) { this.mood = mood; touch(); }

    private void touch() { this.lastUpdated = LocalDateTime.now(); }
}
