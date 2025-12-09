package com.project.library;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> books;

    public Library() {
        this.books = FileHandler.loadBooks();
        if (this.books == null) {
            this.books = new ArrayList<>();
        }
    }

    public void addBook(int id, String title, String author) {
        for (Book b : books) {
            if (b.getId() == id) {
                System.out.println("A book with this ID already exists.");
                return;
            }
        }
        Book book = new Book(id, title, author, false);
        books.add(book);
        FileHandler.saveBooks(books);
        System.out.println("Book added successfully.");
    }

    public void showAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        System.out.println("ID | Title | Author | Status");
        System.out.println("----------------------------------------");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void issueBook(int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                if (b.isIssued()) {
                    System.out.println("This book is already issued.");
                } else {
                    b.setIssued(true);
                    FileHandler.saveBooks(books);
                    System.out.println("Book issued successfully.");
                }
                return;
            }
        }
        System.out.println("Book ID not found.");
    }

    public void returnBook(int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                if (!b.isIssued()) {
                    System.out.println("This book is not currently issued.");
                } else {
                    b.setIssued(false);
                    FileHandler.saveBooks(books);
                    System.out.println("Book returned successfully.");
                }
                return;
            }
        }
        System.out.println("Book ID not found.");
    }
}
