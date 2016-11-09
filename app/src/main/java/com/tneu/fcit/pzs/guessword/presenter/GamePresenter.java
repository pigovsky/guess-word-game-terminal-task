package com.tneu.fcit.pzs.guessword.presenter;

import com.tneu.fcit.pzs.guessword.model.User;
import com.tneu.fcit.pzs.guessword.service.UserService;
import com.tneu.fcit.pzs.guessword.view.GameView;

enum Result { NotFound, WasFound, AlreadyGuessed}

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
        if(guess.equalsIgnoreCase( getSecretWord() ))
        {
            addScore(100);
            gameView.showCongratulations( getSecretWord() );
        }else {
            addScore(-100);
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
        char charLetter = letter.toLowerCase().charAt(0);
        char[] charsOfSecret = getSecretWord().toLowerCase().toCharArray();
        Result myResult = Result.NotFound;

        for(int i = 0; i < getSecretWord().length(); i++)
        {
            if(charsOfSecret[i] == charLetter)
            {
                myResult = Result.WasFound;

                if(getUserCurrentGuess().charAt(i)!='*')
                {
                    myResult = Result.AlreadyGuessed;
                    continue;
                }

                setUserCurrentGuess(getUserCurrentGuess().substring(0,i)+charLetter+getUserCurrentGuess().substring(i+1));
            }
        }

        if(myResult == Result.NotFound) {
            addScore(-1);
            gameView.letterAbsent(letter);

        }
        else if(myResult == Result.WasFound){
            addScore(1);
            gameView.letterHasBeenFound(letter);
        }else
        {
            gameView.letterWasGuessedBefore(letter);
        }

        if(!getUserCurrentGuess().contains("*"))
            gameView.showCongratulations(getSecretWord());


        System.out.println(getUserCurrentGuess());
    }

    private void addScore(int value) {
        user.addScore(value);
        userService.save(user);
    }
}
