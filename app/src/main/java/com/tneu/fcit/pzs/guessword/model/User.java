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
    boolean gender;
    int birthYear;

    public User(String nick, String password) {
        this(nick, password, "user", "", true, 2000);
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

    public int getScore() {
        return score;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setBirthYear(int birth) {
        this.birthYear = birth;
    }

    public void addScore(int value) {
        score += value;
    }
}