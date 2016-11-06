package com.tneu.fcit.pzs.guessword.ui;

import com.tneu.fcit.pzs.guessword.model.User;
import com.tneu.fcit.pzs.guessword.service.UserService;
import com.tneu.fcit.pzs.guessword.service.UserServiceImpl;
import com.tneu.fcit.pzs.guessword.utils.Utils;
import com.tneu.fcit.pzs.guessword.view.GameViewImpl;

/**
 * Created by yp on 02.11.16.
 */
public class WelcomeScreen {

    private final UserService userService = new UserServiceImpl();

    public void showWelcome() {
        System.out.println("Welcome! Please [l]ogin, [r]egister or [s]how best results");
        String line = Utils.SCANNER.nextLine();
        if (line.equalsIgnoreCase("l")) {
            onLogin();
        } else if (line.equalsIgnoreCase("r")) {
            onRegister();
        } else if (line.equalsIgnoreCase("s")) {
            onShowBestResults();
        }
    }

    private void onRegister() {
        System.out.println("Registration is started");

        String nick = promptForAvailableNick();
        String pass = promptForPass();

        User user = new User(nick, pass);
        userService.save(user);
        System.out.println("Hello, " + user.getNick());

        startGameForUser(user);
    }

    /**
     * Asks user to input available for registration nickname
     * @return available nickname
     */
    private String promptForAvailableNick() {
        String nick;

        while (true) {
            nick = promptForNick();
            if (userService.all().get(nick) != null) {
                System.err.printf("The nick %s is busy (", nick);
            } else {
                break;
            }
        }

        return nick;
    }

    private static void startGameForUser(User user) {
        new GameViewImpl(user).gameLoop();
    }

    private void onLogin() {
        System.out.println("Login started");
        User user = loginUserByPass();
        System.out.println("Hello, " + user.getNick());

        startGameForUser(user);
    }

    /**
     * Get User object by asking nick and pass
     * @return User object
     */
    private User loginUserByPass() {
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

        return user;
    }

    private static String promptForPass() {
        System.out.println("Enter your pass, please");
        return Utils.SCANNER.nextLine();
    }

    private static String promptForNick() {
        System.out.println("Enter your nick, please");
        return Utils.SCANNER.nextLine();
    }

    private void onShowBestResults() {

    }
}
