import IO.Printer;
import book.BookList;

import java.util.ResourceBundle;
import java.util.Scanner;

public class BibliotecaApp {

    private Printer printer;
    private Scanner inputScanner;

    public BibliotecaApp(Printer printer, Scanner inputScanner) {

        this.printer = printer;
        this.inputScanner = inputScanner;
    }

    public void run(BookList bookList) {
        printWelcomeMessage();
        printSeparatorLine();
        printMenuList();
        printPrompt();

        inputScanner.useDelimiter("\n");
        while(inputScanner.hasNext()) {
            String option = inputScanner.next();
            Menu menu = Menu.forOption(option);
            menu.perform(bookList,printer);
        }
    }

    private void printMenuList() {
        Menu.printAll(printer);
    }

    private void printPrompt() {
        printer.print("Select Option: ");
    }

    private void printSeparatorLine() {
        String separatorPattern = ResourceBundle.getBundle("BibliotecaAppMessages").getString("SeparatorDesign");
        printer.print(String.format("%-64s", separatorPattern));
    }

    private void printWelcomeMessage() {
        printer.print(ResourceBundle.getBundle("BibliotecaAppMessages").getString("WelcomeMessage"));
    }
}
