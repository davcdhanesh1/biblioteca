package com.biblioteca;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.item.InvalidItemException;
import com.biblioteca.item.ItemCanNotBeReturned;
import com.biblioteca.item.ItemIsNotAvailableForCheckOut;
import com.biblioteca.item.book.Book;
import com.biblioteca.item.book.BookList;
import com.biblioteca.item.movie.Movie;
import com.biblioteca.item.movie.MovieList;
import com.biblioteca.item.movie.Rating;
import com.biblioteca.library.Library;
import com.biblioteca.menu.MenuOptionList;
import com.biblioteca.menu.options.*;
import com.biblioteca.user.InvalidLibraryAndPasswordCombination;
import com.biblioteca.user.User;
import com.biblioteca.user.UserList;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
        MenuOptionList menuList = new MenuOptionList();
        menuList.add(new ListAllBook());
        menuList.add(new ListAllMovies());
        menuList.add(new CheckOutBook());
        menuList.add(new CheckOutMovie());
        menuList.add(new ReturnBook());
        menuList.add(new Login());
        menuList.add(new Quit());

        BookList bookList = new BookList();
        Book harryPotterAndPhilosophersStoneBook = new Book(10, "Harry potters and philosophers Stone", "J K Rowling", 1987);
        Book harryPotterAndChambersOfSecretsBook = new Book(11, "Harry potters and chambers of secrets", "J K Rowling", 1987);
        bookList.add(harryPotterAndPhilosophersStoneBook);
        bookList.add(harryPotterAndChambersOfSecretsBook);

        MovieList movieList = new MovieList();
        Movie whiplashMovie = new Movie(1, "Whiplash", "Damien Chazelle", 2014, Rating.NINE);
        Movie birdmanMovie = new Movie(2, "BirdMan", "Alejandro González Iñárritu", 2014, Rating.TEN);
        movieList.add(whiplashMovie);
        movieList.add(birdmanMovie);

        UserList userList = new UserList();
        User dhanesh = User.customer("777-4445", "Dhanesh", "password", "davcdhanesh1@gmail.com", "9096904102");
        User frank = User.customer("777-4446", "frank", "password", "frank.underwood@gmail.com", "9096904102");
        userList.add(dhanesh);
        userList.add(frank);

        Printer printer = new Printer(System.out);
        Scanner scanner = new Scanner(System.in);

        Library library = new Library(bookList, movieList, printer);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(printer, scanner, userList, library);
        bibliotecaApp.run();
    }
}
