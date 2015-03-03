package com.biblioteca.user;

public class InvalidUserPasswordCombination extends Throwable {
    public InvalidUserPasswordCombination(String msg) {
        super(msg);
    }
}
