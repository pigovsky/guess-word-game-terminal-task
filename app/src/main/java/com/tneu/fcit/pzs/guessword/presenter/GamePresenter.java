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

    /**
     * Перевіряє чи введене слово співпадає із секретним.
     * Коли так, то збільшує рахунок гравця на 100 балів і викликає {@link GameView#showCongratulations(String)},
     * інакше --- зменшує його рахунок на 100 балів і викликає {@link GameView#showGameOver(String)}.
     * <p>
     * Метод нечутливий до регістру букв.
     *
     * @param guess слово, введене користувачем
     */
    public void checkWord(String guess) {
        // TODO: Добавте код, який порівнює введене користувачем слово guess з secretWord (без врахування регістру)
        // слід змінювати рахунок користувача методом addScore (див. нижче) та викликати метод
        // gameView.showCongratulations чи gameView.showGameOver
        if (guess.equalsIgnoreCase(secretWord)) {
            addScore(100);
            gameView.showCongratulations(secretWord);
        } else {
            addScore(-100);
            gameView.showGameOver(secretWord);
        }
    }

    /**
     * Перевіряє чи введена буква присутня у секретному слові.
     * Коли так, то збільшує рахунок гравця на одиницю, замінює у відповідній позиції (позиціях) зірочку в
     * {@link #userCurrentGuess} на вгадану букву і викликає {@link GameView#letterHasBeenFound(String)},
     * інакше --- зменшує його рахунок на одиницю і викликає {@link GameView#letterAbsent(String)}.
     * <p>
     * Метод нечутливий до регістру букв.
     *
     * @param letter введена користувачем літера
     */
    public void checkLetter(String letter) {
        // TODO: Добавте код, який перевіряє чи присутня літера letter у secretWord, модифікує getUserCurrentGuess
        // слід також змінювати рахунок користувача методом addScore та викликати метод gameView.letterHasBeenFound чи
        // gameView.letterAbsent
        if (letter.contains(secretWord)){
            addScore(1);
            gameView.letterHasBeenFound(letter);
        }else {
            addScore(-1);
            gameView.letterAbsent(letter);
        }
    }

    private void addScore(int value) {
        user.addScore(value);
        userService.save(user);
    }
}
