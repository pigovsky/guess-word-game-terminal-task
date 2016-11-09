package com.tneu.fcit.pzs.guessword.ui;

import com.tneu.fcit.pzs.guessword.model.User;
import com.tneu.fcit.pzs.guessword.service.UserService;
import com.tneu.fcit.pzs.guessword.service.UserServiceImpl;
import com.tneu.fcit.pzs.guessword.utils.Utils;
import com.tneu.fcit.pzs.guessword.view.GameViewImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yp on 02.11.16.
 */
public class WelcomeScreen {

    private final UserService userService = new UserServiceImpl();

    public void showWelcome() {
        System.out.println("Welcome! Please [l]ogin or [r]egister or [s]how best results or [a]dd info");
        String line = Utils.SCANNER.nextLine();
        if (line.equalsIgnoreCase("l")) {
            onLogin();
        } else if (line.equalsIgnoreCase("r")) {
            onRegister();
        } else if (line.equalsIgnoreCase("s")) {
            onTable();
        } else if (line.equalsIgnoreCase("a")) {
            addInfo();
        }else
            System.out.println("Unknown command!");
    }

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
        User user = new User(nick, pass);
        System.out.println("Hello, " + user.getNick());
        startGameForUser(user);
    }

    private static void startGameForUser(User user) {
        new GameViewImpl(user).gameLoop();
    }

    private void onLogin() {
        startGameForUser(loggining());
    }
    private User loggining()
    {
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
        System.out.println("Hello, " + user.getNick());
        return  user;
    }
    private void onTable()
    {
        if(userService.all().isEmpty()) {
            System.out.println("There're no users");
            return;
        }

        List<Map.Entry<String, User>> myList = new LinkedList<>(userService.all().entrySet());

        Collections.sort( myList, (o1, o2) -> o1.getValue().compareTo( o2.getValue() ));

        for (Map.Entry<String, User> entry : myList)
        {
            System.out.println(entry.getValue().getNick()+" : "+entry.getValue().getScore());
        }
    }

    private void addInfo()
    {
        User user = loggining();
        System.out.println("First name: "+user.getFirstName() +", LastName: " +user.getLastName() +", Sex: " +user.getSex() +", BirthDay: " +user.getBirth());

        System.out.println("Enter your first name, please");
        user.setFirstName(Utils.SCANNER.nextLine());
        System.out.println("Enter your last name, please");
        user.setLastName(Utils.SCANNER.nextLine());
        System.out.println("Enter your sex, please");
        user.setSex(Utils.SCANNER.nextLine());

        System.out.println("Enter your date of birth, please");
        try {
            String date = Utils.SCANNER.nextLine();
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            user.setBirth(ft.parse(date));
        }catch (ParseException e) {
            System.out.println("Incorrect format of date!");
        }
        userService.save(user);
    }

    private static String promptForPass() {
        System.out.println("Enter your pass, please");
        return Utils.SCANNER.nextLine();
    }

    private static String promptForNick() {
        System.out.println("Enter your nick, please");
        return Utils.SCANNER.nextLine();
    }
}
