package com.tneu.fcit.pzs.guessword.model;

import com.tneu.fcit.pzs.guessword.service.UserService;
import com.tneu.fcit.pzs.guessword.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by VADIM on 01.12.2016.
 */
public class UserTest {

    public static final String NICK = "user";
    public static final String PASS = "password";
    public static final String NAME = "Vadym";
    public static final String SURNAME = "Zabchuk";
    public static final Gender SEX = Gender.Female;

    SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-mm-dd");
    public final Date YEAR = dataFormat.parse("1997-12-17");
    private UserService userService;

    public UserTest() throws ParseException {
    }

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
        assertEquals("wrong pass", SEX, fromBase.getGender());
        assertEquals("wrong pass", YEAR, fromBase.getBirtday());
    }
}