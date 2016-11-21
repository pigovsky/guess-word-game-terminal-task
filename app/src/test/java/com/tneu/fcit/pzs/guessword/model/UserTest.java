package com.tneu.fcit.pzs.guessword.model;

import java.util.Date;
import static org.junit.Assert.*;
/**
 * Created by Ігор on 18.11.2016.
 */
public class UserTest {
    User user1;

    @org.junit.Before
    public void setUp() throws Exception
    {
        user1 = new User("Login","Password");
        user1.setBirth(new Date(1996,9,3));
        user1.setName("Ігор");
        user1.setSurname("Грубель");
        user1.setSex("Male");
    }

    @org.junit.Test
    public void build() throws Exception
    {
        User user2 = new User("Login","Password" , "Ігор" , "Грубель" ,"Male" , new Date(1996,9,3));
        assertEquals(user1.getName(), user2.getName());
        assertEquals(user1.getSurname(), user2.getSurname());
        assertEquals(user1.getSex(), user2.getSex());
        assertEquals(user1.getBirth(), user2.getBirth());
    }
}
