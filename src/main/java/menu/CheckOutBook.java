package menu;

import IO.Printer;
import Library.Library;

public class CheckOutBook extends Menu {
    public CheckOutBook() {
        super("Checkout a Book");
    }

    @Override
    public void perform(Library library, Printer printer) {
        library.printAllBook();
        printer.println("Select a book: ");
    }

    @Override
    public boolean shouldContinueRunning() { return true; }

}
