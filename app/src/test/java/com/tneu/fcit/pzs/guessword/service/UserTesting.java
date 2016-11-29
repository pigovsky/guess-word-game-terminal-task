package com.tneu.fcit.pzs.guessword.service;

import com.tneu.fcit.pzs.guessword.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by gigabaytik on 30.11.16.
 */

public class UserTesting {
    public static final String NICK = "Kevin";
    public static final String PASS = "1234";
    public static final String NAME = "Vitaliy";
    public static final String SURNAME = "Olecsuc";
    public static final boolean GENDER = true;
    public static final int YEAR = 2003;
    private UserService userService;

    @Before
    public void before() {
        userService = new UserServiceImpl();
    }

    @Test
    public void canAddUser() throws Exception {
        User user = new User(NICK, PASS, NAME, SURNAME, GENDER, YEAR);
        userService.save(user);
        User fromBase = userService.check(NICK, PASS);
        assertNotNull("No such user", fromBase);
        assertEquals("wrong nick", NICK, fromBase.getNick());
        assertEquals("wrong pass", PASS, fromBase.getPassword());
        assertEquals("wrong pass", NAME, fromBase.getName());
        assertEquals("wrong pass", SURNAME, fromBase.getSurname());
        assertEquals("wrong pass", GENDER, fromBase.getGender());
        assertEquals("wrong pass", YEAR, fromBase.getBirthYear());
    }
}