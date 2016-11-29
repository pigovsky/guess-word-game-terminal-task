package com.tneu.fcit.pzs.guessword.model;

import com.tneu.fcit.pzs.guessword.utils.Utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yp on 02.11.16.
 */
public class User implements Serializable {
    String nick;
    String password;
    int score;

    private String name;
    private String surname;
    private Gender gender;
    private Date birtday;

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

    public Enum getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirtday() {
        return birtday;
    }

    public void setBirtday(Date birtday) {
        this.birtday = birtday;
    }

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

    public void updateInfo() {

        System.out.println("Do you want update information about yourseft ?(y or n)");

        String line = Utils.SCANNER.nextLine();
        if ( line.equalsIgnoreCase("y") ) {

            System.out.println("Enter you name :");
            setName(Utils.SCANNER.nextLine());

            System.out.println("Enter you surname :");
            setSurname(Utils.SCANNER.nextLine());

            System.out.println("Enter you Birth Year(in format yyyy-mm-dd): ");
            line = Utils.SCANNER.nextLine();

            try{
                SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-mm-dd");
                setBirtday(dataFormat.parse(line));
            }
            catch(ParseException p){
                System.out.format("%s",p.getMessage());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                updateInfo();
            }

            System.out.println("Enter you gender female or male (f,m) :");
            line = Utils.SCANNER.nextLine();
            if ( line.equalsIgnoreCase("f") ) {
                setGender(Gender.Female);
            } else if ( line.equalsIgnoreCase("m") )
                setGender(Gender.Male);
        }

    }


}
