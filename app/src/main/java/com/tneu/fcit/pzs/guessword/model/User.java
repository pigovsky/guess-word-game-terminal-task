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
    String point;
    String FirstName;
    String LastName;
    String Sex;
    Date DOB;


    public User(String nick, String password) {
        this.nick = nick;
        this.password = password;
        this.point = point;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Sex = Sex;
        this.DOB = DOB;
    }

    public String getNick() {
        return nick;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int value) {
        score += value;
    }

    public void setPoint(String point)
    {
        this.point = point;
    }
    public String getPoint()
    {
        return point;
    }
    public void setFirstName(String FirstName)
    {
        this.FirstName = FirstName;
    }
    public String getFirstName()
    {
        return FirstName;
    }
    public void setLastName(String LastName)
    {
        this.LastName = LastName;
    }
    public String getLastName()
    {
        return LastName;
    }
    public void setSex(String Sex)
    {
        this.Sex = Sex;
    }
    public String getSex()
    {
        return Sex;
    }
    public void setDOB(Date DOB)
    {
        this.DOB = DOB;
    }
    public Date getDOB()
    {
        return DOB;
    }
}
