package com.tneu.fcit.pzs.guessword.ui;

import com.tneu.fcit.pzs.guessword.model.User;
import com.tneu.fcit.pzs.guessword.service.UserService;
import com.tneu.fcit.pzs.guessword.service.UserServiceImpl;
import com.tneu.fcit.pzs.guessword.utils.Utils;
import com.tneu.fcit.pzs.guessword.view.GameViewImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by yp on 02.11.16.
 */
public class WelcomeScreen {

    private final UserService userService = new UserServiceImpl();

    public void showWelcome() {
        System.out.println("Welcome! Please [l]ogin or [r]egister  or [s]how best results");
        String line = Utils.SCANNER.nextLine();
        if ( line.equalsIgnoreCase("l") ) {
            onLogin();
        } else if ( line.equalsIgnoreCase("r") ) {
            onRegister();
        }   else if ( line.equalsIgnoreCase("s") ) {
        showBest();
        }
    }
    private void showBest() {

        ArrayList<User> sortedUser = new ArrayList(userService.all().entrySet());

        Collections.sort(sortedUser, (user1, user2) -> {
                    int scoreFirst = user1.getScore();
                    int scoreSecond = user2.getScore();

                    return scoreFirst < scoreSecond ? 1 : (scoreFirst > scoreSecond ? -1 : 0);
                }
        );

        System.out.println("Records");
        for (User user : sortedUser)
            System.out.format("%s\t%s", user.getNick(),user.getScore());

    }


    private void onRegister() {
        System.out.println("Registration is started");
        String nick;
        while (true) {
            nick = promptForNick();
            if ( userService.all().get(nick) != null ) {
                System.err.printf("The nick %s is busy (", nick);
            } else {
                break;
            }
        }
        String pass = promptForPass();
        User user = new User(nick, pass);
        user.updateInfo();
        userService.save(user);
        startGameForUser(user);
    }

    private static void startGameForUser(User user) {
        System.out.println(String.format("Hello, %s !", user.getNick()));
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
        user.updateInfo();
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
}
