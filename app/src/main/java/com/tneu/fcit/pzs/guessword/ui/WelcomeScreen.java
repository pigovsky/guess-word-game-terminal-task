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
        System.out.println("Welcome! Please [l]ogin or [r]egister or [s]how best results or [e]dit profile");
        String line = Utils.SCANNER.nextLine();
        if (line.equalsIgnoreCase("l")) {
            onLogin();
        } else if (line.equalsIgnoreCase("r")) {
            onRegister();
        } else if (line.equalsIgnoreCase("s")) {
            showRecords();
        } else if (line.equalsIgnoreCase("e")) {
            editData();
        }
    }

    public void editProfile(User user, String firstName, String lastName, String sex, Date birthDate) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSex(sex);
        user.setBirth(birthDate);
        userService.save(user);
    }

    public void editData() {
        User user = Loggining();

        System.out.println("Enter your first name");
        user.setFirstName(Utils.SCANNER.nextLine());
        System.out.println("Enter your last name");
        user.setLastName(Utils.SCANNER.nextLine());
        System.out.println("Enter your sex");
        user.setSex(Utils.SCANNER.nextLine());
        System.out.println("Enter your date of birth");
        try {
            String date = Utils.SCANNER.nextLine();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            user.setBirth(ft.parse(date));
        } catch (ParseException e) {
            user.setBirth(null);
            System.out.println("Wrong format of date!");
        }
        userService.save(user);
    }

    private void showRecords() {
        if (!userService.all().isEmpty()) {
            for (Map.Entry<String, User> entry : userService.all().entrySet()) {
                User player = entry.getValue();
                System.out.println(user.getNick() + "-" + user.getScore());
            }
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
        showWelcomeMessage(user);
        startGameForUser(user);
    }

    private void showWelcomeMessage(User user) {
        System.out.println("Hi, " + user.getNick());
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
                break;
            }
        }
        showWelcomeMessage(user);
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
