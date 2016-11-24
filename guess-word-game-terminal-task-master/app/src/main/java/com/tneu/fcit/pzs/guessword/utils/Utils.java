package com.tneu.fcit.pzs.guessword.utils;

import java.util.Scanner;

/**
 * Created by yp on 02.11.16.
 */
public class Utils {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static <T> T getRandomItem(T[] all) {
        return all[(int) (Math.random() * all.length)];
    }
}
