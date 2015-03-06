package com.biblioteca.view;

import com.biblioteca.io.Printer;

import java.util.Scanner;

public class ViewRenderer {
    private String outPutToRender;
    private final Printer printer;
    private final Scanner scanner;

    public ViewRenderer(String outPutToRender, Printer printer, Scanner scanner) {

        this.outPutToRender = outPutToRender;
        this.printer = printer;
        this.scanner = scanner;
    }

    public void render() {
        printer.println(outPutToRender);
    }
}
