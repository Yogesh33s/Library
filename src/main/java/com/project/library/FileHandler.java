package com.project.library;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String FILE_NAME = "books.txt";

    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return books; // empty list
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0].trim());
                    String title = parts[1].trim();
                    String author = parts[2].trim();
                    boolean issued = Boolean.parseBoolean(parts[3].trim());
                    books.add(new Book(id, title, author, issued));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading books file: " + e.getMessage());
        }

        return books;
    }

    public static void saveBooks(List<Book> books) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Book b : books) {
                pw.println(b.getId() + " | " + b.getTitle() + " | " + b.getAuthor() + " | " + b.isIssued());
            }
        } catch (IOException e) {
            System.out.println("Error saving books file: " + e.getMessage());
        }
    }
}
