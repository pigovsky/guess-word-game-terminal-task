package com.tneu.fcit.pzs.guessword.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yp on 02.11.16.
 */
public class User implements Serializable {
    String nick;
    String password;
    int score;
    String surname;

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrth(Date brth) {
        this.brth = brth;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public Date getBrth() {
        return brth;
    }

    String name;
    Date brth;

    public User(String nick, String password) {
        this.nick = nick;
        this.password = password;
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
}
