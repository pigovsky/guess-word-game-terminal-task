package com.tneu.fcit.pzs.guessword.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserTest {
    @Before
    public void setUp() {

    }

    @Test
    public void checkConstructorNickPass() {
        final String expectedNick = "any-random-nick";
        final User user = new User(expectedNick, "any-random-pass");

        assertThat(user.getNick(), is(expectedNick));
    }
}
