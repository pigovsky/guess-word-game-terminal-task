package com.tneu.fcit.pzs.guessword.ui;

import com.tneu.fcit.pzs.guessword.utils.Utils;

enum MenuItem {
    LOGIN("[l]ogin"),
    REGISTER("[r]egister"),
    UPDATE_PROFILE("[u]pdate"),
    SHOW_BEST_RESULTS("[s]ow best results");

    private String name;

    MenuItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class WelcomeMenu {
    public static void showOptions() {
        final MenuItem[] options = MenuItem.values();

        System.out.print("Welcome! Please ");
        for (MenuItem option : options) {
            System.out.print(option.getName() + ", ");
        }

        System.out.println();
    }

    public static MenuItem getSelectedItem() {
        String line = Utils.SCANNER.nextLine();
        if (line.equalsIgnoreCase("l")) {
            return MenuItem.LOGIN;
        }

        if (line.equalsIgnoreCase("r")) {
            return MenuItem.REGISTER;
        }

        if (line.equalsIgnoreCase("u")) {
            return MenuItem.UPDATE_PROFILE;
        }

        if (line.equalsIgnoreCase("s")) {
            return MenuItem.SHOW_BEST_RESULTS;
        }

        return MenuItem.LOGIN;
    }
}
