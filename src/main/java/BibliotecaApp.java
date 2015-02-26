import IO.Printer;
import Library.Library;
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

    public void run(Library library) {
        bootStrapApp();
        Menu menu; String option;
        inputScanner.useDelimiter("\n");
        while(inputScanner.hasNext()) {
            option = inputScanner.next();
            menu = menuList.find(option);
            menu.perform(library, printer);
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
        printer.println("Select Option: ");
    }

    private void printSeparatorLine() {
        printer.println("-----------------------------------------------------------------------------");
    }

    private void printWelcomeMessage() {
        printer.println(ResourceBundle.getBundle("BibliotecaAppMessages").getString("WelcomeMessage"));
    }
}
