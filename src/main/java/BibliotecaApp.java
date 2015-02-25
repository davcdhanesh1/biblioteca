import book.BookList;

import java.util.ResourceBundle;

public class BibliotecaApp {

    public void run(Printer printer, BookList bookList) {
        printer.print(getWelcomeToBibliotecaMsg());
        printer.print(getSeparator());
        printer.print(bookList.toString());
    }

    private String getSeparator() {
        String separatorPattern = ResourceBundle.getBundle("BibliotecaAppMessages").getString("SeparatorDesign");
        return String.format("%-64s",separatorPattern);
    }

    private String getWelcomeToBibliotecaMsg() {
        return ResourceBundle.getBundle("BibliotecaAppMessages").getString("WelcomeMessage");
    }
}
