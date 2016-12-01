package com.tneu.fcit.pzs.guessword.model;

import com.tneu.fcit.pzs.guessword.service.UserService;
import com.tneu.fcit.pzs.guessword.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Opalko on 01.12.2016.
 */
public class UserTest {
    public static final String NICK = "rolerdrom";
    public static final String PASS = "afanut23";
    public static final String NAME = "Oleg";
    public static final String SURNAME = "Opalko";
    public static final boolean SEX = true;
    public static final int YEAR = 1996;
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
        assertEquals("wrong sex", SEX, testedUser.getGender());
        assertEquals("wrong year", YEAR, testedUser.getBirthYear());
    }
}