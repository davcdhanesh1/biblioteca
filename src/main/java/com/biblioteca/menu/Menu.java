package com.biblioteca.menu;

import com.biblioteca.io.Printer;
import com.biblioteca.book.BookIsNotAvailable;
import com.biblioteca.library.Library;
import com.biblioteca.book.BookNotFoundException;

import java.util.Scanner;

public abstract class Menu {

    private final String description;

    Menu(String description) {
        this.description = description;
    }

    public abstract void perform(Library library, Printer printer, Scanner scanner) throws BookNotFoundException, BookIsNotAvailable;

    public abstract boolean shouldContinueRunning();

    @Override
    public String toString() {
        return description;
    }
}
