package com.tneu.fcit.pzs.guessword.ui;

import com.tneu.fcit.pzs.guessword.model.User;
import com.tneu.fcit.pzs.guessword.service.UserService;
import com.tneu.fcit.pzs.guessword.service.UserServiceImpl;
import com.tneu.fcit.pzs.guessword.utils.Utils;
import com.tneu.fcit.pzs.guessword.view.GameViewImpl;

import java.util.*;

/**
 * Created by yp on 02.11.16.
 */
public class WelcomeScreen {

    private final UserService userService = new UserServiceImpl();

    public void showWelcome() {
        System.out.println("Welcome! Please [l]ogin or [r]egister or [s]how best results");
        String line = Utils.SCANNER.nextLine();
        if (line.equalsIgnoreCase("l")) {
            onLogin();
        } else if (line.equalsIgnoreCase("r")) {
            onRegister();
        }
        else if(line.equalsIgnoreCase("s")){
            onRecordTable();
        }
    }
    public void showWhereNotLogIn(){
        System.out.println("Please [t]ry again or [r]egister or [e]xit");
        String line = Utils.SCANNER.nextLine();
        if (line.equalsIgnoreCase("t")) {
            onLogin();
        } else if (line.equalsIgnoreCase("r")) {
            onRegister();
        }
        else if(line.equalsIgnoreCase("e")){
            System.exit(0);
        }
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
        userService.save(user);
        System.out.println(String.format("\nHello, " + user.getNick() + "! Game started! Write \"exit\" to finish game."));
        startGameForUser(user);
    }

    private static void startGameForUser(User user) {
        new GameViewImpl(user).gameLoop();
    }

    private void onLogin() {
        System.out.println("Login started");
        User user;

            String nick = promptForNick();
            String pass = promptForPass();
            user = userService.check(nick, pass);
            if (user == null) {
                System.err.println("Wrong nick or password");
                showWhereNotLogIn();
            }

        System.out.println(String.format("\nHello, " + user.getNick() + "! Game started!  Write \"exit\" to finish game."));
        startGameForUser(user);
    }

    private  void onRecordTable()
    {
        if(userService.all().isEmpty()) {
            System.out.println("There are no users. The database is empty.");
            return;
        }

        List<Map.Entry<String, User>> myList = new LinkedList<>(userService.all().entrySet());
        Collections.sort(myList, new Comparator<Map.Entry<String, User>>() {
            @Override
            public int compare(Map.Entry<String, User> user1, Map.Entry<String, User> user2) {
                return (user1.getValue()).compareTo(user2.getValue());
            }
        });
        int index = 1;
        System.out.println("Table of records:");
        for (Map.Entry<String, User> entry : myList)
        {
            System.out.println(index + ")" +entry.getValue().getNick()+" : "+entry.getValue().getScore());
            index++;
        }
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
