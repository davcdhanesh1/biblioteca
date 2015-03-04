package com.biblioteca.user;

public class InvalidLibraryAndPasswordCombination extends Throwable {
    public InvalidLibraryAndPasswordCombination(String msg) {
        super(msg);
    }
}
