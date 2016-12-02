package com.tneu.fcit.pzs.guessword.model;

import com.tneu.fcit.pzs.guessword.ui.WelcomeScreen;
import java.util.Date;

/**
 * Created by chapelskyi on 02.12.16.
 */
public class EditProfileTest {
    User playerOne;
    String firstName, lastName, sex;
    Date birthDay;

    @org.junit.Before
    public void setUp() throws Exception {
        firstName = "Yaroslavik";
        lastName = "chapelskyi";
        sex = "Male";
        birthDay = new Date(1997, 6, 9);
        playerOne = new User("Login", "Password", firstName, lastName, sex, birthDay);
    }

    @org.junit.Test
    public void build() throws Exception {
        User playerTwo = new User("Login", "Password");
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        welcomeScreen.editProfile(playerTwo, firstName, lastName, sex, birthDay);
        assertEquals(playerOne.getFirstName(), playerTwo.getFirstName());
        assertEquals(playerOne.getLastName(), playerTwo.getLastName());
        assertEquals(playerOne.getSex(), playerTwo.getSex());
        assertEquals(playerOne.getBirth(), playerTwo.getBirth());
    }
}
