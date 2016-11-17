package com.tneu.fcit.pzs.guessword.model;

import com.tneu.fcit.pzs.guessword.service.UserService;
import com.tneu.fcit.pzs.guessword.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class UserTest {
    public static final String NICK = "user10";
    public static final String PASS = "password";
    public static final String NAME = "Vitaliy";
    public static final String SURNAME = "Schur";
    public static final boolean SEX = true;
    public static final int YEAR = 1995;
    private UserService userService;

    @Before
    public void before() {
        userService = new UserServiceImpl();
    }

    @Test
    public void canAddUser() throws Exception {
        User user = new User(NICK, PASS, NAME, SURNAME, SEX, YEAR);
        userService.save(user);
        User fromBase = userService.check(NICK, PASS);
        assertNotNull("No such user", fromBase);
        assertEquals("wrong nick", NICK, fromBase.getNick());
        assertEquals("wrong pass", PASS, fromBase.getPassword());
        assertEquals("wrong pass", NAME, fromBase.getName());
        assertEquals("wrong pass", SURNAME, fromBase.getSurname());
        assertEquals("wrong pass", SEX, fromBase.isSex());
        assertEquals("wrong pass", YEAR, fromBase.getBirthYear());
    }
}
