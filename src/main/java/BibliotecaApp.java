import java.io.IOException;
import java.util.ResourceBundle;

public class BibliotecaApp {
    public static final String WELCOME_TO_BIBLIOTECA_MSG = ResourceBundle.getBundle("BibliotecaAppMessages").getString("WelcomeMessage");

    public void run(Printer printer) throws IOException {
        printer.print(WELCOME_TO_BIBLIOTECA_MSG);
    }
}
