package com.tneu.fcit.pzs.guessword.model;

import java.io.Serializable;

/**
 * Created by yp on 02.11.16.
 */
public class User implements Serializable, Comparable {
    private String nick;
    private String password;
    private int score;

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

    @Override
    public int compareTo(Object o) {
        User user = (User) o;
        return Integer.compare(this.getScore(), user.getScore());
    }

    @Override
    public String toString() {
        return this.nick + " | " + this.score;
    }
}
