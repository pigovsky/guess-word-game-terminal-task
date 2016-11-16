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
     *
     * Метод нечутливий до регістру букв.
     *
     * @param guess слово, введене користувачем
     */
    public void checkWord(String guess) {
        // TODO: Добавте код, який порівнює введене користувачем слово guess з secretWord (без врахування регістру)
        // слід змінювати рахунок користувача методом addScore (див. нижче) та викликати метод
        // gameView.showCongratulations чи gameView.showGameOver


        if (guess.equalsIgnoreCase(secretWord) )
        {
            addScore(100);
            System.out.println("Equal.");
            gameView.showCongratulations(getSecretWord());
        }
        else
        {
            addScore(-100);
            System.out.println("Not equal.");
            gameView.showGameOver(guess);
        }
    }

    /**
     * Перевіряє чи введена буква присутня у секретному слові.
     * Коли так, то збільшує рахунок гравця на одиницю, замінює у відповідній позиції (позиціях) зірочку в
     * {@link #userCurrentGuess} на вгадану букву і викликає {@link GameView#letterHasBeenFound(String)},
     * інакше --- зменшує його рахунок на одиницю і викликає {@link GameView#letterAbsent(String)}.
     *
     * Метод нечутливий до регістру букв.
     *
     * @param letter введена користувачем літера
     */
    public void checkLetter(String letter) {
        // TODO: Добавте код, який перевіряє чи присутня літера letter у secretWord, модифікує getUserCurrentGuess
        // слід також змінювати рахунок користувача методом addScore та викликати метод gameView.letterHasBeenFound чи
        // gameView.letterAbsent
        char charLetter = letter.toLowerCase().charAt(0);
        boolean findResult = false;

        char[] charsOfGuess = getUserCurrentGuess().toCharArray();
        for(int i=0; i<charsOfGuess.length;i++)
        {
            if( Character.toLowerCase(getSecretWord().charAt(i)) == charLetter)
            {
                charsOfGuess[i] = charLetter;

                setUserCurrentGuess(getUserCurrentGuess().substring(0,i)+charLetter+getUserCurrentGuess().substring(i+1));
                findResult = true;
            }
        }

        if(findResult) {
            addScore(1);
            gameView.letterHasBeenFound(letter);
        }
        else
        {
            addScore(-1);
            gameView.letterAbsent(letter);
        }

        if(!getUserCurrentGuess().contains("*"))
            gameView.showCongratulations(getSecretWord());

        System.out.println(getUserCurrentGuess());
    }

    private void addScore(int value) {
        user.addScore(value);
        userService.save(user);
    }

    public void setUserCurrentGuess(String userCurrentGuess) {
        this.userCurrentGuess = userCurrentGuess;
    }
}
