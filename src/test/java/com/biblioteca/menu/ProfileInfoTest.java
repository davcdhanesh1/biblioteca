package com.biblioteca.menu;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProfileInfoTest {

    private ProfileInfo profileInfo;

    @Before
    public void setUp() throws Exception {
        profileInfo = new ProfileInfo();
    }

    @Test
    public void testPerform() throws Exception {

    }

    @Test
    public void testShouldContinueRunning() throws Exception {
        assertThat(profileInfo.shouldContinueRunning(),is(true));
    }

    @Test
    public void testIsSecureLoginRequired() throws Exception {
        assertThat(profileInfo.isSecureLoginRequired(),is(true));
    }
}