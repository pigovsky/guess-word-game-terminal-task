package com.tneu.fcit.pzs.guessword.model;

import java.io.Serializable;

/**
 * Created by yp on 02.11.16.
 */
public class User implements Serializable {
    String nick;
    String password;
    int score;
    String name;
    String surname;
    boolean sex;
    int birthYear;

    public User(String nick, String password) {
        this(nick, password, "user", "", true, 2000);
    }

    public User(String nick, String password, String name, String surname, boolean sex, int birthYear) {
        this.nick = nick;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.birthYear = birthYear;
    }

    public String getNick() {
        return nick;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean getSex() {
        return sex;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int value) {
        score += value;
    }
}
