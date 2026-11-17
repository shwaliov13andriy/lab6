package org.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("1. СХОВИЩЕ ТАЙТЛІВ (файл v0_titles.txt)");
        System.out.println("==================================================");
        processTitles("v0_titles.txt");

        System.out.println("\n==================================================");
        System.out.println("2. СХОВИЩЕ КОРИСТУВАЧІВ (файл v0_users.txt)");
        System.out.println("==================================================");
        processUsers("v0_users.txt");
    }

    private static void processTitles(String filePath) {
        RecordRepository<String, Title> titleRepo = new RecordRepository<>();
        int lineCount = 0;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {

            String header = reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                lineCount++;
                String[] parts = line.split(";");
                String id = parts[0];
                String name = parts[1];
                String type = parts[2];
                int year = Integer.parseInt(parts[3]);
                double rating = Double.parseDouble(parts[4]);

                titleRepo.save(new Title(id, name, type, year, rating));
            }
        } catch (IOException e) {
            System.out.println("Помилка читання файлу " + filePath + ": " + e.getMessage());
            return;
        }

        System.out.println("Тайтли: рядків у файлі " + lineCount + ", записів у сховищі " + titleRepo.size());

        printTitleSearch(titleRepo, "T-1017");
        printTitleSearch(titleRepo, "T-2521");
        printTitleSearch(titleRepo, "T-0000");

        List<Title> sortedByYear = titleRepo.getSorted(new TitleByYearComparator());
        System.out.println("\nЗа роком виходу (" + sortedByYear.size() + " записів):");
        for (Title t : sortedByYear) {
            System.out.println(t.getYear() + " " + t.getName() + " (рейтинг: " + t.getRating() + ")");
        }

        List<Title> sortedByRating = titleRepo.getSorted(new TitleByRatingComparator());
        System.out.println("\nЗа рейтингом (усього у відсортованому результаті " + sortedByRating.size() + " записів, топ-5):");
        for (int i = 0; i < 5 && i < sortedByRating.size(); i++) {
            Title t = sortedByRating.get(i);
            System.out.println(t.getRating() + " " + t.getName() + " (" + t.getYear() + ")");
        }

        int countForEach = 0;
        for (Title t : titleRepo) {
            countForEach++;
        }
        System.out.println("\nОбхід сховища тайтлів через for-each успішний: пройдено " + countForEach + " записів.");

        try {
            Iterator<Title> it = titleRepo.iterator();
            if (it.hasNext()) {
                it.next();
                it.remove();
            }
        } catch (UnsupportedOperationException e) {
            System.out.println("Захист стану працює: спробу видалення через iterator.remove() заблоковано (UnsupportedOperationException).");
        }
    }

    private static void printTitleSearch(RecordRepository<String, Title> repo, String key) {
        Title found = repo.findByKey(key);
        if (found != null) {
            System.out.println("Пошук " + key + ": " + found.getName() + ", " + found.getYear() + " (рейтинг: " + found.getRating() + ")");
        } else {
            System.out.println("Пошук " + key + ": не знайдено");
        }
    }

    private static void processUsers(String filePath) {
        RecordRepository<String, User> userRepo = new RecordRepository<>();
        int lineCount = 0;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {

            String header = reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                lineCount++;
                String[] parts = line.split(";");
                String login = parts[0];
                String fullName = parts[1];
                String registrationDate = parts[2];

                userRepo.save(new User(login, fullName, registrationDate));
            }
        } catch (IOException e) {
            System.out.println("Помилка читання файлу " + filePath + ": " + e.getMessage());
            return;
        }

        System.out.println("Користувачі: рядків у файлі " + lineCount + ", записів у сховищі " + userRepo.size());

        printUserSearch(userRepo, "nova36");
        printUserSearch(userRepo, "rocket79");
        printUserSearch(userRepo, "user_0000");

        List<User> sortedByDate = userRepo.getSorted(new UserByDateComparator());
        System.out.println("\nЗа датою реєстрації (" + sortedByDate.size() + " записів):");
        for (User u : sortedByDate) {
            System.out.println(u.getRegistrationDate() + " " + u.getLogin() + " — " + u.getFullName());
        }

        List<User> sortedByName = userRepo.getSorted(new UserByNameComparator());
        System.out.println("\nЗа ПІБ (усього у відсортованому результаті " + sortedByName.size() + " записів, перші 5):");
        for (int i = 0; i < 5 && i < sortedByName.size(); i++) {
            User u = sortedByName.get(i);
            System.out.println(u.getFullName() + " (" + u.getLogin() + ", " + u.getRegistrationDate() + ")");
        }
    }

    private static void printUserSearch(RecordRepository<String, User> repo, String login) {
        User found = repo.findByKey(login);
        if (found != null) {
            System.out.println("Пошук " + login + ": " + found.getFullName() + ", " + found.getRegistrationDate());
        } else {
            System.out.println("Пошук " + login + ": не знайдено");
        }
    }
}