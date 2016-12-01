package com.tneu.fcit.pzs.guessword.model;

public enum Gender {
    MALE, FEMALE, OTHER;

    public static Gender parse(String input) {
        if (input.equalsIgnoreCase("male")) {
            return MALE;
        }

        if (input.equalsIgnoreCase("female")) {
            return FEMALE;
        }

        return OTHER;
    }
}
