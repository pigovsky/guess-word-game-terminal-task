package com.tneu.fcit.pzs.guessword.ui;

import com.tneu.fcit.pzs.guessword.model.User;
import com.tneu.fcit.pzs.guessword.service.UserService;
import com.tneu.fcit.pzs.guessword.service.UserServiceImpl;
import com.tneu.fcit.pzs.guessword.utils.Utils;
import com.tneu.fcit.pzs.guessword.view.GameViewImpl;

import java.util.Scanner;

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
        startGameForUser(user);
    }

    private static void startGameForUser(User user) {
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
                System.out.println("Hello, " + nick + " !");
                break;
            }
        }

        System.out.println("What do you want to do next ?");
        System.out.println("[s]tart game, [e]dit my profile");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if (line.equalsIgnoreCase("e")) {

            System.out.println("Enter new name: ");
            String newName = scanner.nextLine();
            user.setName(newName);

            System.out.println("Enter new surname: ");
            user.setSurname(scanner.nextLine());

            System.out.println("Enter new year of birth: ");
            user.setBirthYear(scanner.nextLine());

            System.out.println("Enter new sex: ");
            user.setSex(scanner.nextLine());

            System.out.println("Your new profile info: ");
            System.out.println("1. Name " + user.getName());
            System.out.println("2. Surname " + user.getSurname());
            System.out.println("3. Year of birth " + user.getBirthYear());
            System.out.println("4. Sex " + user.getSex());
            System.out.println("Press any key to start game: ");
            scanner.next();
            startGameForUser(user);
        }else
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
