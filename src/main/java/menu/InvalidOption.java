package menu;

import IO.Printer;
import Library.Library;

public class InvalidOption extends Menu {

    public InvalidOption() {
        super("Invalid Option!");
    }

    @Override
    public void perform(Library library, Printer printer) {
        printer.println("Invalid option!");
    }

    @Override
    public boolean shouldContinueRunning() {
        return true;
    }

}
