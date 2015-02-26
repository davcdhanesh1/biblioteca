package menu;

import IO.Printer;
import Library.Library;

public abstract class Menu {

    private final String description;

    Menu(String description) {
        this.description = description;
    }

    public abstract void perform(Library library, Printer printer);

    public abstract boolean shouldContinueRunning();

    @Override
    public String toString() {
        return description;
    }
}
