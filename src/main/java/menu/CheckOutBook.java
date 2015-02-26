package menu;

import IO.Printer;
import library.Library;
import book.BookNotFoundException;

import java.util.Scanner;

public class CheckOutBook extends Menu {
    public CheckOutBook() {
        super("Checkout a Book");
    }

    @Override
    public void perform(Library library, Printer printer, Scanner scanner) throws BookNotFoundException {
        String option;
        library.printAllBook();
        printer.println("Select a book: ");
        option = scanner.next();
        library.checkOut(option);
    }
    @Override
    public boolean shouldContinueRunning() { return true; }

}
