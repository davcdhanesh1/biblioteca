package com.biblioteca.menu;

import com.biblioteca.book.BookCanNotBeReturned;
import com.biblioteca.io.Printer;
import com.biblioteca.book.BookIsNotAvailableForCheckOut;
import com.biblioteca.book.BookNotFoundException;
import com.biblioteca.library.Library;

import java.util.Scanner;

public class ReturnBook extends Menu{
    public ReturnBook() {
        super("Return a Book");
    }

    @Override
    public void perform(Library library, Printer printer, Scanner scanner) throws BookNotFoundException, BookCanNotBeReturned {
        printer.println("Enter id of Book: ");
        String option = scanner.next();
        library.returnBook(option);
    }

    public boolean shouldContinueRunning() {
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
