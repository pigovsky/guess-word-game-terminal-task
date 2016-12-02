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

    public String setUserCurrentGuess(String guessWord) {
        userCurrentGuess = guessWord;
    }

    /**
     * Перевіряє чи введене слово співпадає із секретним.
     * Коли так, то збільшує рахунок гравця на 100 балів і викликає {@link GameView#showCongratulations(String)},
     * інакше --- зменшує його рахунок на 100 балів і викликає {@link GameView#showGameOver(String)}.
     * <p/>
     * Метод нечутливий до регістру букв.
     *
     * @param guess слово, введене користувачем
     */
    public void checkWord(String guess) {
        if (guess.egualsIgnoreCase(getSecretWord())) {
            addScore(100);
            gameView.showCongratulations("Correct word is " + getSecretWord());
        } else {
            addScore(-100);
            gameView.showGameOver(guess + " is a wrong word");
        }
    }

    /**
     * Перевіряє чи введена буква присутня у секретному слові.
     * Коли так, то збільшує рахунок гравця на одиницю, замінює у відповідній позиції (позиціях) зірочку в
     * {@link #userCurrentGuess} на вгадану букву і викликає {@link GameView#letterHasBeenFound(String)},
     * інакше --- зменшує його рахунок на одиницю і викликає {@link GameView#letterAbsent(String)}.
     * <p/>
     * Метод нечутливий до регістру букв.
     *
     * @param letter введена користувачем літера
     */
    public void checkLetter(String letter) {
        char charLetter = letter.toLowerCase().charAt(0);
        char[] charsOfSecret = getSecretWord().toLowerCase().toCharArray();
        String searchResult = "Not found";

        for (int i = 0; i < getSecretWord().length(); i++) {
            if (charsOfSecret[i] == charLetter) {
                searchResult = "Was found";

                if (getUserCurrentGuess().charAt(i) != '*') {
                    searchResult = "Letter was guessed before";
                }
                setUserCurrentGuess(getUserCurrentGuess().substring(0, i) + charLetter + getUserCurrentGuess().substring(i + 1));
            }
        }

        if (searchResult == "Not found") {
            addScore(-1);
            gameView.letterAbsent(letter);
        } else if (searchResult == "Was found") {
            addScore(1);
            gameView.letterHasBeenFound(letter);
        } else {
            gameView.letterWasGuessedBefore(letter);
        }
    }

    private void addScore(int value) {
        user.addScore(value);
        userService.save(user);
    }
}
