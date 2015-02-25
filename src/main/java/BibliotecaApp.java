import IO.Printer;
import book.BookList;
import menu.Menu;

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
        Menu menu;

        inputScanner.useDelimiter("\n");
        while(inputScanner.hasNext()) {
            String option = inputScanner.next();
            menu = Menu.forOption(option);
            menu.perform(bookList, printer);
        }
    }

    private void printMenuList() {
        Menu.printAll(printer);
    }

    private void printPrompt() {
        printer.print("Select Option: ");
    }

    private void printSeparatorLine() {
        printer.print("-----------------------------------------------------------------------------");
    }

    private void printWelcomeMessage() {
        printer.print(ResourceBundle.getBundle("BibliotecaAppMessages").getString("WelcomeMessage"));
    }
}
