package menu;

import IO.Printer;
import Library.Library;

public class ListAllBook extends Menu{

    public ListAllBook() {
        super("List Books");
    }

    @Override
    public void perform(Library library, Printer printer) {
        library.printAllBook();
    }

    @Override
    public boolean shouldContinueRunning() {
        return true;
    }
}
