package menu;

import io.Printer;
import book.BookIsNotAvailable;
import library.Library;
import book.BookNotFoundException;

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
