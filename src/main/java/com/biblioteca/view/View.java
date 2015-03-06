package com.biblioteca.view;

import com.biblioteca.io.Printer;

import java.util.Scanner;

public class View {
    private String outPutToRender;
    private final Printer printer;
    private final Scanner scanner;

    public View(String outPutToRender, Printer printer, Scanner scanner) {

        this.outPutToRender = outPutToRender;
        this.printer = printer;
        this.scanner = scanner;
    }

    public View(Printer printer, Scanner scanner) {

        this.printer = printer;
        this.scanner = scanner;
    }

    public void render() {
        printer.println(outPutToRender);
    }

    public void render(String output) {
        printer.println(output);
    }

    public String scan() {
        scanner.useDelimiter("\n");
        if (scanner.hasNext()) {
            return scanner.next();
        }
        return null;
    }
}
