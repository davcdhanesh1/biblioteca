package com.biblioteca.inputValidator;

import junit.framework.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    public void testForBlankInput() throws Exception, InputValidationException {
        try {
            Validator.validate("     ");
            fail("Failed. Test should have raised an InputValidationException exception");
        } catch (InputValidationException e) {
            assertThat(e.getMessage(), is("Input can't be empty"));
        }
    }

    @Test
    public void testNegativeInput() throws Exception, InputValidationException {
        try {
            Validator.validate("-1");
            fail("Failed. Test should have raised an InputValidationException exception");
        } catch (InputValidationException e) {
            assertThat(e.getMessage(), is("Input can't be 0 or less than 0"));
        }
    }

    @Test
    public void testCharacterInput() throws Exception {
        Character[] inputs = {'a', '*'};

        for(Character ch: inputs) {
            try {
                Validator.validate(ch.toString());
                fail("Failed. Test should have raised an InputValidationException exception");
            } catch (InputValidationException e) {
                assertThat(e.getMessage(), is("Input has to be number"));
            }
        }
    }

    @Test
    public void testSpecialCharacter() throws Exception {
        Character[] inputs = {'\n', '\t'};

        for(Character ch: inputs) {
            try {
                Validator.validate(ch.toString());
                fail("Failed. Test should have raised an InputValidationException exception");
            } catch (InputValidationException e) {
                assertThat(e.getMessage(), is("Input can't be empty"));
            }
        }
    }
}