package menu;

import IO.Printer;
import book.BookIsNotAvailable;
import book.BookNotFoundException;
import library.Library;

import java.util.Scanner;

public class ReturnBook extends Menu{
    public ReturnBook() {
        super("Return a Book");
    }

    @Override
    public void perform(Library library, Printer printer, Scanner scanner) throws BookNotFoundException, BookIsNotAvailable {
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
