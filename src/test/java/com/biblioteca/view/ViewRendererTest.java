package com.biblioteca.view;

import com.biblioteca.io.Printer;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static junit.framework.TestCase.assertEquals;

public class ViewRendererTest {
    @Test
    public void testRender() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Printer printer = new Printer(byteArrayOutputStream);

        String input = new String();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(byteArrayInputStream);
        ViewRenderer viewRenderer = new ViewRenderer("Hi output", printer, scanner);
        viewRenderer.render();
        assertEquals(byteArrayOutputStream.toString(), "Hi output\n");
    }

    @Test
    public void testRenderWithString() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Printer printer = new Printer(byteArrayOutputStream);

        String input = new String();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(byteArrayInputStream);

        ViewRenderer viewRenderer = new ViewRenderer(printer, scanner);
        viewRenderer.render("Hi output");

        assertEquals(byteArrayOutputStream.toString(), "Hi output\n");
    }

    @Test
    public void testReadInput() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Printer printer = new Printer(byteArrayOutputStream);

        String input = "1\ninput\n";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(byteArrayInputStream);

        ViewRenderer viewRenderer = new ViewRenderer(printer, scanner);

        String inputFromView = viewRenderer.scan();
        assertEquals("1", inputFromView);
        inputFromView = viewRenderer.scan();
        assertEquals("input", inputFromView);
    }
}