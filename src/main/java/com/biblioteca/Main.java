package com.biblioteca;

import com.biblioteca.inputValidator.InputValidationException;
import com.biblioteca.io.Printer;
import com.biblioteca.exceptions.InvalidItemException;
import com.biblioteca.exceptions.ItemCanNotBeReturned;
import com.biblioteca.exceptions.ItemIsNotAvailableForCheckOut;
import com.biblioteca.model.rental.Book;
import com.biblioteca.model.rental.BookList;
import com.biblioteca.model.rental.RentedItemList;
import com.biblioteca.model.rental.Movie;
import com.biblioteca.model.rental.MovieList;
import com.biblioteca.model.rental.Rating;
import com.biblioteca.model.Library;
import com.biblioteca.view.menuOptions.*;
import com.biblioteca.exceptions.InvalidLibraryAndPasswordCombination;
import com.biblioteca.model.User;
import com.biblioteca.model.UserList;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InvalidItemException, ItemIsNotAvailableForCheckOut, ItemCanNotBeReturned, InputValidationException, InvalidLibraryAndPasswordCombination {
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
        User dhanesh = User.admin("777-4445", "Dhanesh", "password", "davcdhanesh1@gmail.com", "9096904102");
        User frank = User.customer("777-4446", "frank", "password", "frank.underwood@gmail.com", "9096904102");
        userList.add(dhanesh);
        userList.add(frank);

        Printer printer = new Printer(System.out);
        Scanner scanner = new Scanner(System.in);

        RentedItemList rentedItemList = new RentedItemList();
        Library library = new Library(bookList, movieList, rentedItemList);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(printer, scanner, userList, library);
        bibliotecaApp.run();
    }
}
