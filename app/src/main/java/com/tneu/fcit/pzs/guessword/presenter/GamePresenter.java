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
        if (secretWord.toLowerCase().equals(guess.toLowerCase())) {
            addScore(100);
            gameView.showCongratulations(secretWord);
        } else {
            addScore(-100);
            gameView.showGameOver(secretWord);
        }
        // TODO: Добавте код, який порівнює введене користувачем слово guess з secretWord (без врахування регістру)
        // слід змінювати рахунок користувача методом addScore (див. нижче) та викликати метод
        // gameView.showCongratulations чи gameView.showGameOver
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
        if (secretWord.toLowerCase().contains(letter.toLowerCase())) {
            StringBuilder Word = new StringBuilder(userCurrentGuess);
            for (int i = 0; i < secretWord.length(); i++)
                if (secretWord.substring(i) == letter)
                    Word.setCharAt(i, letter.charAt(0));
            userCurrentGuess = Word.toString();

            addScore(1);
            gameView.letterHasBeenFound(letter.toLowerCase());
        } else {
            addScore(-1);
            gameView.letterAbsent(letter.toLowerCase());
        }

        // TODO: Добавте код, який перевіряє чи присутня літера letter у secretWord, модифікує getUserCurrentGuess
        // слід також змінювати рахунок користувача методом addScore та викликати метод gameView.letterHasBeenFound чи
        // gameView.letterAbsent
    }

    private void addScore(int value) {
        user.addScore(value);
        userService.save(user);
    }
}
