package com.biblioteca.inputValidator;

public class Validator {
    public static void validate(String input) throws InputValidationException {
        String trimmedInput = input.trim();
        if (trimmedInput.length() == 0) throw new InputValidationException("Input can't be empty");

        try {
            if (Integer.parseInt(trimmedInput) <= 0) throw new InputValidationException("Input can't be 0 or less than 0");
        } catch (NumberFormatException e) {
            throw new InputValidationException("Input has to be number");
        }
    }
}
