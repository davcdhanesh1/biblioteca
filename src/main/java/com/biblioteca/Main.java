package com.biblioteca;

import com.biblioteca.book.*;
import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.library.Library;
import com.biblioteca.menu.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws BookNotFoundException, BookIsNotAvailableForCheckOut, BookCanNotBeReturned, InputValidationException {
        MenuList menuList = new MenuList();
        menuList.add(new ListAllBook());
        menuList.add(new CheckOutBook());
        menuList.add(new ReturnBook());
        menuList.add(new Quit());

        BookList bookList = new BookList();
        Book harryPotterAndPhilosophersStoneBook = new Book(10, "Harry potters and philosophers Stone", "J K Rowling", 1987);
        Book harryPotterAndChambersOfSecretsBook = new Book(11, "Harry potters and chambers of secrets", "J K Rowling", 1987);
        bookList.add(harryPotterAndPhilosophersStoneBook);
        bookList.add(harryPotterAndChambersOfSecretsBook);

        Printer printer = new Printer(System.out);
        Scanner scanner = new Scanner(System.in);

        Library library = new Library(bookList, printer);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(printer, scanner, menuList);
        bibliotecaApp.run(library);
    }
}
