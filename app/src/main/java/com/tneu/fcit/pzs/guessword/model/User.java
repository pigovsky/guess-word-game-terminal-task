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
    Date date;
    String pol;

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
	
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getDate() {
        return date;
    }

    public String getPol() {
        return pol;
    }

    public void setName(String str){
	name = str;
    }

    public void setSurname(String str){
	surname = str;
    }

    public void setDate(Date d){
	date = d;
    }

    public void setPol(String str){
	pol = str;
    }

    public void addScore(int value) {
        score += value;
    }
}
