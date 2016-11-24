package com.tneu.fcit.pzs.guessword.service;

import com.tneu.fcit.pzs.guessword.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yp on 02.11.16.
 */
public class UserServiceTest {

    public static final String NICK = "nick";
    public static final String NICK2 = "nick2";
    public static final String PASS = "pass";
    public static final String PASS2 = "pass2";
    private UserService userService;

    @Before
    public void before() {
        userService = new UserServiceImpl();
    }

    @Test
    public void canAddUser() throws Exception {
        User user = new User(NICK, PASS);
        userService.save(user);
        User fromBase = userService.check(NICK, PASS);
        assertNotNull("No such user", fromBase);
        assertEquals("wrong nick", NICK, fromBase.getNick());
        assertEquals("wrong pass", PASS, fromBase.getPassword());
    }

    @Test
    public void canAddTwoUsers() throws Exception {
        User user = new User(NICK, PASS);
        User user2 = new User(NICK2, PASS2);
        userService.save(user);
        userService.save(user2);
        User fromBase = userService.check(NICK, PASS);
        User fromBase2 = userService.check(NICK2, PASS2);
        assertNotNull("No such user", fromBase);
        assertNotNull("No such user", fromBase2);
        assertEquals("wrong nick", NICK, fromBase.getNick());
        assertEquals("wrong pass", PASS, fromBase.getPassword());
        assertEquals("wrong nick", NICK2, fromBase2.getNick());
        assertEquals("wrong pass", PASS2, fromBase2.getPassword());
    }

}