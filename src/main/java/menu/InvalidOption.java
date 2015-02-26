package menu;

import IO.Printer;
import library.Library;

import java.util.Scanner;

public class InvalidOption extends Menu {

    public InvalidOption() {
        super("Invalid Option!");
    }

    @Override
    public void perform(Library library, Printer printer, Scanner scanner) {
        printer.println("Invalid option!");
    }

    @Override
    public boolean shouldContinueRunning() {
        return true;
    }

}
