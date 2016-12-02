package com.tneu.fcit.pzs.guessword.model;

import java.io.Serializable;

/**
 * Created by yp on 02.11.16.
 */
public class User implements Serializable {
    String nick;
    String password;
    String firstName;
    String lastName;
    String sex;
    Date birth;

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    int score;

    public User(String nick, String password) {
        this.nick = nick;
        this.password = password;
    }

    public User(int score, Date birth, String sex, String lastName, String firstName, String password, String nick) {
        this.score = score;
        this.birth = birth;
        this.sex = sex;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
        this.nick = nick;
    }

    public User(int score) {
        this.score = score;
    }

    public User(String nick, String password, String firstName, String lastName, String sex, Date birth) {

        this.nick = nick;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birth = birth;
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
