import IO.Printer;
import book.BookList;
import menu.Menu;
import menu.MenuList;

import java.util.ResourceBundle;
import java.util.Scanner;

public class BibliotecaApp {

    private Printer printer;
    private Scanner inputScanner;
    private MenuList menuList;

    public BibliotecaApp(Printer printer, Scanner inputScanner, MenuList menuList) {

        this.printer = printer;
        this.inputScanner = inputScanner;
        this.menuList = menuList;
    }

    public void run(BookList bookList) {
        bootStrapApp();
        Menu menu;
        inputScanner.useDelimiter("\n");
        while(inputScanner.hasNext()) {
            String option = inputScanner.next();
            menu = menuList.find(option);
            menu.perform(bookList, printer);
            if(!menu.shouldContinueRunning()) {
                break;
            }
        }
    }

    private void bootStrapApp() {
        printWelcomeMessage();
        printSeparatorLine();
        printMenuList();
        printPrompt();
    }

    private void printMenuList() {
        menuList.printAll(printer);
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
