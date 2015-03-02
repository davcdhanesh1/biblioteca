package com.biblioteca.menu;

import com.biblioteca.item.BookCanNotBeReturned;
import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.inputValidator.Validator;
import com.biblioteca.io.Printer;
import com.biblioteca.item.BookNotFoundException;
import com.biblioteca.library.Library;

import java.util.Scanner;

public class ReturnBook extends Menu{
    public ReturnBook() {
        super("Return a Book");
    }

    @Override
    public void perform(Library library, Printer printer, Scanner scanner) throws BookNotFoundException, BookCanNotBeReturned, InputValidationException {
        printer.println("Enter id of Book: ");
        String option = scanner.next();
        Validator.validate(option);
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
