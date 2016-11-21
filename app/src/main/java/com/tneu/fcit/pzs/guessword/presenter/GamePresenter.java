package com.tneu.fcit.pzs.guessword.presenter;

import com.tneu.fcit.pzs.guessword.model.User;
import com.tneu.fcit.pzs.guessword.service.UserService;
import com.tneu.fcit.pzs.guessword.view.GameView;

/**
 * Created by yp on 02.11.16.
 */
enum IsFound { not, yes, exist}

public class GamePresenter {
    private final User user;
    private final UserService userService;
    private final GameView gameView;
    private final String secretWord;
    private String userCurrentGuess;

    public GamePresenter(User user, UserService userService, GameView gameView, String secretWord) {
        this.user = user;
        this.userService = userService;
        this.gameView = gameView;
        this.secretWord = secretWord;
        userCurrentGuess = new String(new char[secretWord.length()]).replace('\0', '*');
    }

    public User getUser() {
        return user;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public String getUserCurrentGuess() {
        return userCurrentGuess;
    }

    public void setUserCurrentGuess(String guessString) {
        userCurrentGuess = guessString;
    }

    public void checkWord(String guess) {
        if(guess.equalsIgnoreCase( getSecretWord() ))
        {
            addScore(100);
            gameView.showCongratulations( getSecretWord() );
        }else {
            addScore(-100);
            gameView.showGameOver(guess);
        }
    }

    public void checkLetter(String letter) {
        char symbol = letter.toLowerCase().charAt(0);
        char[] charsInSecretWord = getSecretWord().toLowerCase().toCharArray();
        IsFound isFound = IsFound.not;
        for (int i = 0; i < getSecretWord().length(); i++) {
            if (charsInSecretWord[i] == symbol) {
                if (getUserCurrentGuess().charAt(i) == symbol) {
                    isFound = IsFound.exist;
                } else {
                    isFound = IsFound.yes;
                    if (i == 0) {
                        setUserCurrentGuess(String.format(getUserCurrentGuess().substring(0, i) + Character.toUpperCase(symbol) + getUserCurrentGuess().substring(i + 1)));
                    } else {
                        setUserCurrentGuess(String.format(getUserCurrentGuess().substring(0, i) + symbol + getUserCurrentGuess().substring(i + 1)));
                    }
                }
            }
        }
        if (isFound == IsFound.yes) {
            addScore(1);
            gameView.letterHasBeenFound(letter);
        } else if (isFound == IsFound.exist) {
            gameView.letterAlreadyFound(letter);
        } else {
            addScore(-1);
            gameView.letterAbsent(letter);
        }

        System.out.println(getUserCurrentGuess());

        if (!getUserCurrentGuess().contains("*"))
            gameView.showCongratulations(getSecretWord());
    }

    private void addScore(int value) {
        user.addScore(value);
        userService.save(user);
    }
}
