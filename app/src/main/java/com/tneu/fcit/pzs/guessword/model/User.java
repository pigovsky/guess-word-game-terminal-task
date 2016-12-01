package com.tneu.fcit.pzs.guessword.model;

import java.io.Serializable;

/**
 * Created by yp on 02.11.16.
 */

public class User implements Serializable {
    String nick;
    String password;
    int score;
    String nameU;
    String surnameU;
    //String kind;
    int birthY;
    boolean kind;

    /**
     *
     * @param nick
     * @param password
     * @param nameU
     * @param surnameU
     * @param kind
     * @param birthY
     */
    public User(String nick, String password, String nameU, String surnameU, boolean kind, int birthY) {
        this.nick = nick;
        this.password = password;
        this.nameU = nameU;
        this.surnameU = surnameU;

        this.kind = kind;
        this.birthY = birthY;

    }

    /**
     * standard constructor for user which has not made the data itself     P.S. I know my english is bad =)
     * @param nick
     * @param password
     */
    public User(String nick, String password) {
        this(nick, password, "Без", " імені", true, 2016);
    }

    public String getNick() {
        return nick;
    }

    public String getPassword() {
        return password;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int value) {
        score += value;
    }

    public String getNameU() {
        return nameU;
    }

    public String getSurnameU() {
        return surnameU;
    }

    public boolean getKind() {
        return kind;
    }

    public int getBirthY() {
        return birthY;
    }
}
