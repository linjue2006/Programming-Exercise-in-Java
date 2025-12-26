package com.fop.app;

import com.fop.model.JournalEntry;
import com.fop.model.User;
import com.fop.service.AuthService;
import com.fop.storage.JournalStorage;
import com.fop.storage.UserStorage;
import com.fop.util.GreetingUtil;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;
import com.fop.api.HuggingFaceClient;
import com.fop.api.WeatherClient;

public class Main {

    // 你现在 VSCode 的工作目录在外层，所以先用 smart-journal 前缀保证能跑
    private static final Path USER_FILE = Path.of("smart-journal", "data", "UserData.txt");
    private static final Path JOURNAL_ROOT = Path.of("smart-journal", "data", "journals");

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);


        // 1) load users
        var userStorage = new UserStorage(USER_FILE);
        var users = userStorage.loadUsers();
        var auth = new AuthService(users, userStorage);

        // 2) login loop
        User current = null;

while (current == null) {
    System.out.println("=== Smart Journal ===");
    System.out.println("1) Login");
    System.out.println("2) Sign Up");
    System.out.println("0) Exit");
    System.out.print("Choose: ");
    String choice = sc.nextLine().trim();

    if (choice.equals("0")) {
        System.out.println("Bye!");
        return;
    }

    if (choice.equals("2")) {
        try {
            System.out.println("\n=== Sign Up ===");
            System.out.print("Email: ");
            String email = sc.nextLine().trim();

            System.out.print("Display Name: ");
            String name = sc.nextLine().trim();

            System.out.print("Password: ");
            String pw = sc.nextLine().trim();

            auth.register(email, name, pw);
            System.out.println("Sign Up successful! Please login.\n");
        } catch (Exception e) {
            System.out.println("Sign Up failed: " + e.getMessage() + "\n");
        }
        continue;
    }

    if (choice.equals("1")) {
        System.out.print("Email: ");
        String email = sc.nextLine().trim();
        System.out.print("Password: ");
        String pw = sc.nextLine().trim();

        current = auth.login(email, pw);
        if (current == null) {
            System.out.println("Login failed. Try again.\n");
        }
        continue;
    }

    System.out.println("Invalid choice.\n");
}


        // 3) welcome + menu
        System.out.println("\n" + GreetingUtil.greeting() + ", " + current.getDisplayName() + "!");
        var journalStorage = new JournalStorage(JOURNAL_ROOT);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1) Create/Edit/View Journals");
            System.out.println("2) View Weekly Mood Summary (placeholder)");
            System.out.println("0) Logout");
            System.out.print("Choose: ");

            String choice = sc.nextLine().trim();
            if (choice.equals("0")) {
                System.out.println("Bye!");
                break;
            } else if (choice.equals("1")) {
                handleJournals(sc, journalStorage, current.getEmail());
            } else if (choice.equals("2")) {
                handleWeeklySummary(journalStorage, current.getEmail());

            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private static void handleJournals(Scanner sc, JournalStorage js, String email) throws Exception {
    while (true) {
        System.out.println("\n=== Journals (last 7 days) ===");

        LocalDate today = LocalDate.now();
        // 列出最近 7 天（含今天）
        for (int i = 0; i < 7; i++) {
            LocalDate d = today.minusDays(i);
            boolean exists = js.entryExists(email, d);
            String tag = d.equals(today) ? " (TODAY)" : "";
            String status = exists ? "HAS ENTRY" : "NO ENTRY";
            System.out.printf("%d) %s%s  [%s]%n", (i + 1), d, tag, status);
        }

        System.out.println("0) Back to main menu");
        System.out.print("Choose a date (1-7): ");
        String input = sc.nextLine().trim();

        if ("0".equals(input)) return;

        int choice;
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number 0-7.");
            continue;
        }

        if (choice < 1 || choice > 7) {
            System.out.println("Please enter a number 1-7.");
            continue;
        }

        LocalDate selected = today.minusDays(choice - 1);

        // 过去日期：只允许查看
        if (!selected.equals(today)) {
            var loaded = js.loadEntry(email, selected);
            System.out.println("\n--- View Only: " + selected + " ---");
            if (loaded.isEmpty()) {
                System.out.println("No entry for this date.");
            } else {
                JournalEntry e = loaded.get();
                System.out.println("weather=" + e.getWeather());
                System.out.println("mood=" + e.getMood());
                System.out.println("text=" + e.getText());
            }
            System.out.println("(Past entries are view-only)");
            continue;
        }

        // 今天：允许创建/编辑
        handleTodayEditable(sc, js, email, today);
    }
}
private static void handleWeeklySummary(JournalStorage js, String email) throws Exception {
    System.out.println("\n=== Weekly Mood Summary (last 7 days) ===");

    LocalDate today = LocalDate.now();

    for (int i = 0; i < 7; i++) {
        LocalDate d = today.minusDays(i);
        var opt = js.loadEntry(email, d);

        if (opt.isEmpty()) {
            System.out.println(d + " | (no entry)");
        } else {
            var e = opt.get();
            String weather = (e.getWeather() == null || e.getWeather().isBlank()) ? "(unknown)" : e.getWeather();
            String mood = (e.getMood() == null || e.getMood().isBlank()) ? "(unknown)" : e.getMood();
            System.out.println(d + " | " + weather + " | " + mood);
        }
    }
}

private static void handleTodayEditable(Scanner sc, JournalStorage js, String email, LocalDate today) throws Exception {
    var loaded = js.loadEntry(email, today);
    JournalEntry entry;

    if (loaded.isEmpty()) {
        System.out.println("\nToday entry not found. Creating new...");
        entry = new JournalEntry(today);
    System.out.println("Fetching weather for Kunming..."); 
            String w = WeatherClient.getCurrentWeather();
            entry.setWeather(w);
    System.out.println("Current Weather: " + w);
    } else {
        entry = loaded.get();
        System.out.println("\nToday entry exists.");
        System.out.println("weather=" + entry.getWeather());
        System.out.println("mood=" + entry.getMood());
        System.out.println("text=" + entry.getText());
    }

    System.out.println("\nEnter your journal text (single line for now):");
    String text = sc.nextLine();
    entry.setText(text);
try {
    String label = HuggingFaceClient.inferSentimentLabel(text); // 例如 POSITIVE / NEGATIVE
    if (label != null) {
        label = label.toUpperCase();
        if (label.contains("POSITIVE")) entry.setMood("POSITIVE");
        else if (label.contains("NEGATIVE")) entry.setMood("NEGATIVE");
        else entry.setMood("NEUTRAL");
    }
    System.out.println("API mood label = " + label);
} catch (Exception ex) {
    System.out.println("API failed, keep mood as-is. reason=" + ex.getMessage());
}

    js.saveEntry(email, entry);
    System.out.println("Saved today entry: " + today);
}

}

