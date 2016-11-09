package com.tneu.fcit.pzs.guessword.ui;

import com.tneu.fcit.pzs.guessword.model.User;
import java.util.Date;

import static org.junit.Assert.*;


public class AddInfoTest {
    User firstUser;
    String firstName, lastName, sex;
    Date birthDay;
    @org.junit.Before
    public void setUp() throws Exception
    {
        firstName = "Andriy";lastName = "Pylypchuk";sex = "Male";
        birthDay = new Date(1997,1,25);
        firstUser = new User("Login","Password",firstName,lastName,sex,birthDay);
    }

    @org.junit.Test
    public void build() throws Exception
    {
        User secondUser = new User("Login","Password");
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        welcomeScreen.addInfo(secondUser,firstName,lastName,sex,birthDay);
        assertEquals(firstUser.getFirstName() , secondUser.getFirstName());
        assertEquals(firstUser.getLastName()  , secondUser.getLastName());
        assertEquals(firstUser.getSex()       , secondUser.getSex());
        assertEquals(firstUser.getBirth()     , secondUser.getBirth());
    }

}