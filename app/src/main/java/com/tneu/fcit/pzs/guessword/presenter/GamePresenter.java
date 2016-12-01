package com.tneu.fcit.pzs.guessword.presenter;

import com.tneu.fcit.pzs.guessword.model.User;
import com.tneu.fcit.pzs.guessword.service.UserService;
import com.tneu.fcit.pzs.guessword.view.GameView;

/**
 * Created by yp on 02.11.16.
 */
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

    private void setUserCurrentGuess(String guess) {
        userCurrentGuess = guess;
    }

    /**
     * Check if input guess is correct one and print game results
     * @param guess User input word
     */
    public void checkWord(String guess) {
        boolean isCorrectGuess = this.secretWord.equalsIgnoreCase(guess);

        if (isCorrectGuess) {
            addScore(100);
            this.gameView.showCongratulations(getSecretWord());
        } else {
            addScore(-100);
            this.gameView.showGameOver(guess);
        }
    }

    /**
     * Check if input letter is correct and print game results
     * @param letter User input letter
     */
    public void checkLetter(String letter) {
        boolean isLetterInSecretWord = checkLetterInSecretWord(letter);

        if (isLetterInSecretWord) {
            String currentGuessState = unveilLetterFromSecretWord(letter);
            setUserCurrentGuess(currentGuessState);
            addScore(1);
            this.gameView.letterHasBeenFound(letter);
        } else {
            addScore(-1);
            this.gameView.letterAbsent(letter);
        }
    }

    private void addScore(int value) {
        user.addScore(value);
        userService.save(user);
    }

    private boolean checkLetterInSecretWord(String letter) {
        return getSecretWord().toLowerCase().contains(letter.toLowerCase());
    }

    private String unveilLetterFromSecretWord(String letter) {
        final String guessedLettersRegex = "[^" + letter.toUpperCase() + letter.toLowerCase() + "]";
        return getSecretWord().replaceAll(guessedLettersRegex, "*");
    }
}
