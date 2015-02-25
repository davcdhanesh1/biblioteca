import IO.Printer;
import book.BookList;
import menu.MenuOption;

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
        MenuOption menuOption;

        inputScanner.useDelimiter("\n");
        while(inputScanner.hasNext()) {
            String option = inputScanner.next();
            menuOption = MenuOption.forOption(option);
            menuOption.perform(bookList, printer);
        }
    }

    private void printMenuList() {
        MenuOption.printAll(printer);
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
