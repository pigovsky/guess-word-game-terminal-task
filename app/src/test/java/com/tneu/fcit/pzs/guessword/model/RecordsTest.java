package com.tneu.fcit.pzs.guessword.model;

/**
 * Created by chapelsyi on 02.12.16.
 */
public class RecordsTest {
    @org.junit.Test
    public void build() throws Exception {
        User playerOne = new User(100);
        User playerTwo = new User(10);
        assertEquals(playerOne.compareTo(playerTwo), -1);
    }
}
