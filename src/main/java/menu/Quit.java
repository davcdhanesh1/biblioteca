package menu;

import IO.Printer;
import Library.Library;

public class Quit extends Menu {

    public Quit() {
        super("Quit");
    }

    @Override
    public void perform(Library library, Printer printer) {
        printer.println("Book a week, keeps teacher away!");
    }

    @Override
    public boolean shouldContinueRunning() {
        return false;
    }
}
