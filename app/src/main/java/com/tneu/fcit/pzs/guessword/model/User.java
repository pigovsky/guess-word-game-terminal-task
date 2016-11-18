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
    String name;
    String surname;
    String sex;
    Date birth;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthYear() {
        return birth;
    }

    public void setBirthYear(Date birth) {
        this.birth = birth;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int compareTo(User value) {
        if(this.getScore() > value.getScore())
            return -1;
        else if(this.getScore() == value.getScore())
            return 0;
        else return 1;
    }
}
