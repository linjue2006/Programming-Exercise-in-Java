package com.fop.storage;

import com.fop.model.JournalEntry;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class JournalStorage {
    private final Path journalsRoot; // e.g. smart-journal/data/journals

    public JournalStorage(Path journalsRoot) {
        this.journalsRoot = journalsRoot;
    }

    public Optional<JournalEntry> loadEntry(String email, LocalDate date) throws IOException {
        Path file = entryFile(email, date);
        if (!Files.exists(file)) return Optional.empty();

        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);

        Map<String, String> header = new HashMap<>();
        StringBuilder text = new StringBuilder();
        boolean inText = false;

        for (String line : lines) {
            if (!inText) {
                if (line.equals("text=")) {
                    inText = true;
                } else {
                    int idx = line.indexOf('=');
                    if (idx > 0) header.put(line.substring(0, idx).trim(), line.substring(idx + 1).trim());
                }
            } else {
                text.append(line).append("\n");
            }
        }

        JournalEntry e = new JournalEntry(date);
        e.setWeather(header.getOrDefault("weather", ""));
        e.setMood(header.getOrDefault("mood", ""));
        e.setText(text.toString().stripTrailing());
        return Optional.of(e);
    }
public boolean entryExists(String email, LocalDate date) {
    Path file = entryFile(email, date);
    return Files.exists(file);
}

    public void saveEntry(String email, JournalEntry entry) throws IOException {
        Path file = entryFile(email, entry.getDate());
        Files.createDirectories(file.getParent());

        List<String> out = new ArrayList<>();
        out.add("date=" + entry.getDate());
        out.add("weather=" + nullToEmpty(entry.getWeather()));
        out.add("mood=" + nullToEmpty(entry.getMood()));
        out.add("lastUpdated=" + LocalDateTime.now());
        out.add("text=");
        if (entry.getText() != null && !entry.getText().isEmpty()) {
            out.addAll(Arrays.asList(entry.getText().split("\\R", -1)));
        }

        Files.write(file, out, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    private Path entryFile(String email, LocalDate date) {
        String safe = email.toLowerCase(Locale.ROOT)
                .replace("@", "_at_")
                .replace(".", "_dot_");
        return journalsRoot.resolve(safe).resolve(date + ".txt");
    }

    private String nullToEmpty(String s) { return s == null ? "" : s; }
}

