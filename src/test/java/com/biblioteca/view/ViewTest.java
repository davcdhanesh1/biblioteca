package com.biblioteca.view;

import com.biblioteca.io.Printer;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static junit.framework.TestCase.assertEquals;

public class ViewTest {
    @Test
    public void testRender() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Printer printer = new Printer(byteArrayOutputStream);

        String input = new String();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(byteArrayInputStream);
        View view = new View("Hi output", printer, scanner);
        view.render();
        assertEquals(byteArrayOutputStream.toString(), "Hi output\n");
    }

    @Test
    public void testRenderWithString() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Printer printer = new Printer(byteArrayOutputStream);

        String input = new String();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(byteArrayInputStream);

        View view = new View(printer, scanner);
        view.render("Hi output");

        assertEquals(byteArrayOutputStream.toString(), "Hi output\n");
    }

    @Test
    public void testReadInput() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Printer printer = new Printer(byteArrayOutputStream);

        String input = "1\ninput\n";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(byteArrayInputStream);

        View view = new View(printer, scanner);

        String inputFromView = view.scan();
        assertEquals("1", inputFromView);
        inputFromView = view.scan();
        assertEquals("input", inputFromView);
    }
}