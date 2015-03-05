package com.biblioteca.user;

public class InvalidLibraryAndPasswordCombination extends Exception {
    public InvalidLibraryAndPasswordCombination(String msg) {
        super(msg);
    }
}
