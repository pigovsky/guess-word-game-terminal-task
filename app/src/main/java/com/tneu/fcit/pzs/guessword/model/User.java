package com.tneu.fcit.pzs.guessword.model;

import java.io.Serializable;

/**
 * Created by yp on 02.11.16.
 */
public class User implements Serializable {
    private String nick;
    private String password;
    private int score;
    private String name;
    private String surname;
    private boolean gender;
    private int birthYear;

    public User(String nick, String password) {
        this(nick, password, "player", "", true, 1995);
    }

    public User(String nick, String password, String name, String surname, boolean gender, int birthYear) {
        this.nick = nick;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthYear = birthYear;
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

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean getGender() {
        return gender;
    }

    public int getBirthYear() {
        return birthYear;
    }
}
