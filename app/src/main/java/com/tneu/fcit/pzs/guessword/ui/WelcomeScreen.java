package com.tneu.fcit.pzs.guessword.ui;

import com.tneu.fcit.pzs.guessword.model.User;
import com.tneu.fcit.pzs.guessword.service.UserService;
import com.tneu.fcit.pzs.guessword.service.UserServiceImpl;
import com.tneu.fcit.pzs.guessword.utils.Utils;
import com.tneu.fcit.pzs.guessword.view.GameViewImpl;
import java.util.*;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;

/**
 * Created by yp on 02.11.16.
 */
public class WelcomeScreen {

    private final UserService userService = new UserServiceImpl();

    public void showWelcome() {
        System.out.println("===Welcome!===");System.out.println();
        System.out.println("--> Please [l] on keyboard for login");
        System.out.println("--> Please [r] on keyboard for register");
        System.out.println("--> Please [s] on keyboard for show");System.out.println();
        String line = Utils.SCANNER.nextLine();
        if (line.equalsIgnoreCase("l")) {
            onLogin();
        } else if (line.equalsIgnoreCase("r")) {
            onRegister();
        } else if (line.equalsIgnoreCase("s")) {
            onShow();
        }
    }

    public String defoultName="Без";
    public String defoultSurName=" iмені";
    public int defoultBirthY=2015;
    //public true - це стать чоловіча, false-жіноча (ну тому що як би хлопець-1; дівчина-0) =)
    public boolean defoultKind=true;

    private void onRegister() {
        System.out.println("Registration is started");
        String nick;
        while (true) {
            nick = promptForNick();
            if (userService.all().get(nick) != null) {
                System.err.printf("The nick %s is busy (", nick);
            } else {
                break;
            }
        }
        String pass = promptForPass();
        String nameU = promptForNameU(defoultName);
        String surnameU = promptForSurNameU(defoultSurName);
        boolean kind = promptForKind(defoultKind);
        int birthY = promptForBirthY(defoultBirthY);
        User user = new User(nick, pass, nameU, surnameU, kind, birthY);
        userService.save(user);
        startGameForUser(user);
    }

    private static void startGameForUser(User user) {
        System.out.println("Hello, "+user.getNick()+" !");
        new GameViewImpl(user).gameLoop();
    }

    private void onLogin() {
        System.out.println("Login started");
        User user;
        while (true) {
            String nick = promptForNick();
            String pass = promptForPass();
            user = userService.check(nick, pass);
            if (user == null) {
                System.err.println("Wrong nick or password");
            } else {
                break;
            }
        }
        startGameForUser(user);
    }

    private static String promptForPass() {
        System.out.println("Enter your pass, please");
        return Utils.SCANNER.nextLine();
    }

    private static String promptForNick() {
        System.out.println("Enter your nick, please");
        return Utils.SCANNER.nextLine();
    }

    private static String promptForNameU(String defoultName) {
        System.out.println("Enter your name, please");
        String name=Utils.SCANNER.nextLine();
        if(name==null || name=="" || name==" ")return defoultName;
        else return name;
    }

    private static String promptForSurNameU(String defoultSurName) {
        System.out.println("Enter your name, please");
        String surname=Utils.SCANNER.nextLine();
        if(surname==null || surname=="" || surname==" ")return defoultSurName;
        else return surname;
    }

    private static boolean promptForKind(boolean defoultKind) {
        while (true) {
            System.out.println("Enter your kind: [m]en or [w]omen");
            String kindU = Utils.SCANNER.nextLine();
            if (kindU.toLowerCase().equals("m")) {
                defoultKind = true;
                break;
            } else if (kindU.toLowerCase().equals("w")) {
                defoultKind = false;
                break;
            } else
                System.out.println("Enter your kind: [m]en or [w]omen, jast one letter");
        }
        return defoultKind;
    }

    private static int promptForBirthY(int defoultBirthY) {
        while (true) {
            System.out.println("Enter your birth year, please");
            try {
                defoultBirthY = Utils.SCANNER.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Enter real year");
            }
        }
        return defoultBirthY;
    }

    private void onShow() {
        System.out.println("Records table:");
        for (Map.Entry<String,User> entry : userService.all().entrySet()){
            User user=entry.getValue();
            System.out.println(user.getNick() + " - [" + user.getScore()+"]");
        }
        showWelcome();
    }
}
