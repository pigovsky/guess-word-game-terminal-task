package com.tneu.fcit.pzs.guessword.ui;

import com.tneu.fcit.pzs.guessword.model.User;

import static org.junit.Assert.*;

public class TableOfRecordsTest {
    @org.junit.Test
    public void build() throws Exception
    {
        User firstUser = new User(100);
        User secondUser = new User(10);
        assertEquals(firstUser.compareTo(secondUser),-1);
    }
}