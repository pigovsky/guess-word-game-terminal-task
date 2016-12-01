package com.tneu.fcit.pzs.guessword.ui;

import com.tneu.fcit.pzs.guessword.model.User;
import com.tneu.fcit.pzs.guessword.presenter.UserPresenter;
import com.tneu.fcit.pzs.guessword.service.UserService;
import com.tneu.fcit.pzs.guessword.service.UserServiceImpl;
import com.tneu.fcit.pzs.guessword.utils.Utils;
import com.tneu.fcit.pzs.guessword.view.GameViewImpl;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by yp on 02.11.16.
 */
public class WelcomeScreen {

    private final UserService userService = UserServiceImpl.getInstance();
    private final UserPresenter userPresenter = new UserPresenter(userService);

    private static void startGameForUser(User user) {
        new GameViewImpl(user).gameLoop();
    }

    private static String promptForPass() {
        System.out.println("Enter your pass, please");
        return Utils.SCANNER.nextLine();
    }

    private static String promptForNick() {
        System.out.println("Enter your nick, please");
        return Utils.SCANNER.nextLine();
    }

    public void showWelcome() {
        WelcomeMenu.showOptions();
        startGameFlow();
    }

    private void startGameFlow() {
        final MenuItem selectedItem = WelcomeMenu.getSelectedItem();

        if (selectedItem == MenuItem.LOGIN) {
            onLogin();
            return;
        }

        if (selectedItem == MenuItem.REGISTER) {
            onRegister();
            return;
        }

        if (selectedItem == MenuItem.UPDATE_PROFILE) {
            onUpdateProfile();
            return;
        }

        if (selectedItem == MenuItem.SHOW_BEST_RESULTS) {
            onShowBestResults();
            return;
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

    private void onUpdateProfile() {
        System.out.println("Update Profile is started");
        final User user = loginUserByPass();
        userPresenter.updateProfile(user);
    }

    /**
     * Asks user to input available for registration nickname
     *
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

    private void onLogin() {
        System.out.println("Login started");
        User user = loginUserByPass();
        System.out.println("Hello, " + user.getNick());

        startGameForUser(user);
    }

    /**
     * Get User object by asking nick and pass
     *
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

    /**
     * Show table with results for all users in descending order
     */
    private void onShowBestResults() {
        final Map<String, User> allUsers = userService.all();
        final int usersCount = allUsers.size();

        if (usersCount <= 0) {
            System.out.println("Sorry, there are no users to display results for");
            return;
        }

        System.out.println("There are " + usersCount + " users in the table");
        System.out.println("| name | score |");
        System.out.println("| ---- | ----- |");

        Stream.of(allUsers.values().toArray())
                .sorted(Collections.reverseOrder())
                .forEach(user -> System.out.println("| " + user + " |"));

        System.out.println("| ---- | ----- |");
    }
}
