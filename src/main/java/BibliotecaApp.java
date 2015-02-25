import IO.OutPutPrinter;
import book.BookList;

import java.util.ResourceBundle;
import java.util.Scanner;

public class BibliotecaApp {

    public void run(OutPutPrinter outPutPrinter, BookList bookList) {
        outPutPrinter.print(getWelcomeToBibliotecaMsg());
        outPutPrinter.print(getSeparator());
        outPutPrinter.print(bookList.toString());
    }

    private String getSeparator() {
        String separatorPattern = ResourceBundle.getBundle("BibliotecaAppMessages").getString("SeparatorDesign");
        return String.format("%-64s",separatorPattern);
    }

    private String getWelcomeToBibliotecaMsg() {
        return ResourceBundle.getBundle("BibliotecaAppMessages").getString("WelcomeMessage");
    }
}
