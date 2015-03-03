package com.biblioteca.user;

import com.biblioteca.item.Item;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserTest {

    @Test
    public void shouldReturnTrueIfUserMatches() throws Exception {
        User user = new User("777-4445", "Dhanesh", "davcdhanesh1@gmail.com", "9096904102", new ArrayList<Item>(10));
        String expectedUserString = "|777-4445|Dhanesh                         |davcdhanesh1@gmail.com          |9096904102";
        assertThat(user.toString(),is(expectedUserString));
    }
}