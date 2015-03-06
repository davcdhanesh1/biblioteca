package com.biblioteca.exceptions;

public class InvalidLibraryAndPasswordCombination extends Exception {
    public InvalidLibraryAndPasswordCombination(String msg) {
        super(msg);
    }
}
