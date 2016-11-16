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
    String secondName;
    String point;
    Date birthDay;


    public User(String nick, String password) {
        this.nick = nick;
        this.password = password;
        this.name = name;
        this.secondName = secondName;
        this.point = point;
        this.birthDay = birthDay;
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
    //setters

    public void setName(String name) {
        this.name = name;
    }
    public void setSecondName(String secondName)
    {
        this.secondName =secondName;
    }
    public void setPoint(String point)
    {
        this.point = point;
    }
    public  void  setBirthDay(Date birthDay)
    {
        this.birthDay = birthDay;
    }
    // getters
    public String getName() {
        return name;
    }
    public String getSecondName()
    {
        return secondName;
    }
    public String getPoint()
    {
        return point;
    }
    public  Date  getBirthDay()
    {
        return birthDay;
    }
}
