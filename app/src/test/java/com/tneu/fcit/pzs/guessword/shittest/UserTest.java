package com.tneu.fcit.pzs.guessword.shittest;

import com.tneu.fcit.pzs.guessword.model.User;
import com.tneu.fcit.pzs.guessword.service.UserService;
import com.tneu.fcit.pzs.guessword.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by nazar on 25-Nov-16.
 */
public class UserTest {
        public static final String NICK = "Uswerg";
        public static final String PASS = "qwerty";
        public static final String NAME = "Ahmed";
        public static final String SURNAME = "Hitler";
        public static final boolean SEX = false;
        public static final int YEAR = 95;
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
            assertEquals("wrong pass", SEX, fromBase.getSex());
            assertEquals("wrong pass", YEAR, fromBase.getBirthYear());
        }
}
