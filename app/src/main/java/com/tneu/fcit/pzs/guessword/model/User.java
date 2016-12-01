package com.tneu.fcit.pzs.guessword.model;

import java.io.Serializable;

/**
 * Created by yp on 02.11.16.
 */
public class User implements Serializable, Comparable {
    private String nick;
    private String password;
    private int score;
    private String name;
    private String surname;
    private int birthYear;
    private Gender gender;

    public User(String nick, String password) {
        this.nick = nick;
        this.password = password;
    }

    public User(String nick, String password, Gender gender, String name, String surname, int birthYear) {
        this.nick = nick;
        this.password = password;
        this.gender = gender;
        this.name = name;
        this.surname = surname;
        this.birthYear = birthYear;
    }

    public String getNick() {
        return nick;
    }

    public int getScore() {
        return score;
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

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void addScore(int value) {
        score += value;
    }

    public boolean isPasswordCorrect(String password) {
        return this.password.equals(password);
    }

    public void printShortInfo() {
        System.out.println("Your current info is: ");
        System.out.println("Name: " + this.getName());
        System.out.println("Surname: " + this.getSurname());
        System.out.println("Birth year: " + this.getBirthYear());
        System.out.println("Gender: " + this.getGender());
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
