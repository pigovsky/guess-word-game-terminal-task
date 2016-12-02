package com.tneu.fcit.pzs.guessword.view;

/**
 * Created by yp on 03.11.16.
 */
public interface GameView {
    void showCurrentUserGuess(String userCurrentGuess);

    void letterAbsent(String letter);

    void letterHasBeenFound(String letter);

    void showGameOver(String guess);

    void showCongratulations(String secretWord);

    void letterAlreadyFound(String letter);

}
