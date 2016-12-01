package com.tneu.fcit.pzs.guessword.model;

import com.tneu.fcit.pzs.guessword.service.UserService;
import com.tneu.fcit.pzs.guessword.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Grinash on 17-Nov-16.
 */
public class UserTest {
    public static final String NICK = "Pzs32Player";
    public static final String PASS = "qwerty";
    public static final String NAME = "Pavlo";
    public static final String SURNAME = "Grinash";
    public static final boolean SEX = true;
    public static final int YEAR = 1997;
    private UserService userService;

    @Before
    public void before() {
        userService = new UserServiceImpl();
    }

    @Test
    public void canAddUser() throws Exception {
        User user = new User(NICK, PASS, NAME, SURNAME, SEX, YEAR);
        userService.save(user);
        User testedUser = userService.check(NICK, PASS);
        assertNotNull("No such user", testedUser);
        assertEquals("wrong nick", NICK, testedUser.getNick());
        assertEquals("wrong pass", PASS, testedUser.getPassword());
        assertEquals("wrong name", NAME, testedUser.getName());
        assertEquals("wrong surname", SURNAME, testedUser.getSurname());
        assertEquals("wrong sex", SEX, testedUser.getSex());
        assertEquals("wrong year", YEAR, testedUser.getBirthYear());
    }
}