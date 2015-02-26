package menu;

import IO.Printer;
import Library.Library;
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
        scanner.useDelimiter("\n");
        while(scanner.hasNext()) {
            option = scanner.next();
            library.checkOut(option);
        }
    }
    @Override
    public boolean shouldContinueRunning() { return true; }

}
