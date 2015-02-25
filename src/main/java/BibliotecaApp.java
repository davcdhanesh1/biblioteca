import IO.OutPutPrinter;
import book.BookList;

import java.util.ResourceBundle;

public class BibliotecaApp {

    private OutPutPrinter outPutPrinter;

    public BibliotecaApp(OutPutPrinter outPutPrinter) {

        this.outPutPrinter = outPutPrinter;
    }

    public void run(BookList bookList) {
        printWelcomeMessage();
        printSeparatorLine();
        printMenuList();
        printPrompt();
    }

    private void printMenuList() {
        Menu.printAll(outPutPrinter);
    }

    private void printPrompt() {
        outPutPrinter.print("Select Option: ");
    }

    private void printSeparatorLine() {
        String separatorPattern = ResourceBundle.getBundle("BibliotecaAppMessages").getString("SeparatorDesign");
        outPutPrinter.print(String.format("%-64s", separatorPattern));
    }

    private void printWelcomeMessage() {
        outPutPrinter.print(ResourceBundle.getBundle("BibliotecaAppMessages").getString("WelcomeMessage"));
    }
}
