package com.biblioteca;

import com.biblioteca.book.Book;
import com.biblioteca.book.BookIsNotAvailable;
import com.biblioteca.book.BookList;
import com.biblioteca.book.BookNotFoundException;
import com.biblioteca.io.Printer;
import com.biblioteca.library.Library;
import com.biblioteca.menu.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws BookNotFoundException, BookIsNotAvailable {
        MenuList menuList = new MenuList();
        menuList.add(new ListAllBook());
        menuList.add(new CheckOutBook());
        menuList.add(new ReturnBook());
        menuList.add(new Quit());

        BookList bookList = new BookList();
        Book harryPotterAndPhilosophersStoneBook = new Book(1, "Harry potters and philosophers Stone", "J K Rowling", 1987);
        Book harryPotterAndChambersOfSecretsBook = new Book(1, "Harry potters and chambers of secrets", "J K Rowling", 1987);
        bookList.add(harryPotterAndPhilosophersStoneBook);
        bookList.add(harryPotterAndChambersOfSecretsBook);

        Printer printer = new Printer(System.out);
        Scanner scanner = new Scanner(System.in);

        Library library = new Library(bookList, printer);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(printer, scanner, menuList);
        bibliotecaApp.run(library);
    }
}
