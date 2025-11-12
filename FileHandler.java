package controller;

import model.Book;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

public class FileHandler {

    public static void saveToFile(List<Book> books, String filePath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(books);
            System.out.println("Об'єкти бібліотеки успішно серіалізовано та збережено: " + filePath);
        } catch (IOException e) {
            System.out.println("Помилка серіалізації або запису у файл: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Book> loadFromFile(String filePath) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            List<Book> books = (List<Book>) in.readObject();
            System.out.println("Об'єкти бібліотеки успішно десеріалізовано з файлу: " + filePath);
            return books;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Помилка десеріалізації або читання файлу: " + e.getMessage());
            return List.of();
        }
    }

    public static String getLongestLine(String filePath) {
        String maxLine = "";
        int maxWords = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int words = line.trim().split("\\s+").length;
                if (words > maxWords) {
                    maxWords = words;
                    maxLine = line;
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        }
        return maxLine;
    }

    public static void encryptFile(String inputPath, String outputPath, char key) {
        try (FileReader fr = new FileReader(inputPath);
             FileWriter fw = new FileWriter(outputPath)) {
            int ch;
            while ((ch = fr.read()) != -1) {
                fw.write(ch + key);
            }
            System.out.println("Файл успішно зашифровано: " + outputPath);
        } catch (IOException e) {
            System.out.println("Помилка шифрування: " + e.getMessage());
        }
    }

    public static void decryptFile(String inputPath, String outputPath, char key) {
        try (FileReader fr = new FileReader(inputPath);
             FileWriter fw = new FileWriter(outputPath)) {
            int ch;
            while ((ch = fr.read()) != -1) {
                fw.write(ch - key);
            }
            System.out.println("Файл успішно розшифровано: " + outputPath);
        } catch (IOException e) {
            System.out.println("Помилка дешифрування: " + e.getMessage());
        }
    }

    public static void analyzeTags(String url) {
        try {
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "https://" + url;
            }

            URL website = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(website.openStream()));
            String inputLine;
            Map<String, Integer> tagCount = new HashMap<>();

            Pattern pattern = Pattern.compile("<\\s*(\\w+)");
            while ((inputLine = in.readLine()) != null) {
                Matcher matcher = pattern.matcher(inputLine.toLowerCase());
                while (matcher.find()) {
                    String tag = matcher.group(1);
                    tagCount.put(tag, tagCount.getOrDefault(tag, 0) + 1);
                }
            }
            in.close();

            System.out.println("\n Теги в алфавітному порядку:");
            tagCount.keySet().stream().sorted().forEach(t ->
                    System.out.println(t + " → " + tagCount.get(t)));

            System.out.println("\n Теги за зростанням частоти:");
            tagCount.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(e -> System.out.println(e.getKey() + " → " + e.getValue()));

        } catch (IOException e) {
            System.out.println("Помилка доступу до сторінки: " + e.getMessage());
        }
    }
}
