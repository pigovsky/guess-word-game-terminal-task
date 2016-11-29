package com.tneu.fcit.pzs.guessword.presenter;

import com.tneu.fcit.pzs.guessword.model.User;
import com.tneu.fcit.pzs.guessword.service.UserService;
import com.tneu.fcit.pzs.guessword.view.GameView;

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

    public void setGuess(String string) { userCurrentGuess = string; }

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

             public void checkLetter(String letter) {
                 if( getSecretWord().contains(letter.toLowerCase())) {
                     letter = letter.toLowerCase();
                     char[] secretChars = getSecretWord().toCharArray();
                     char[] guessChars  = getUserCurrentGuess().toCharArray();
                     for(int i=0; i< getSecretWord().length();i++)
                     {
                         if(secretChars[i] == letter.charAt(0))
                         {
                             guessChars[i] = secretChars[i];
                             addScore(1);
                             gameView.letterHasBeenFound(letter);
                         }
                     }
                     setGuess(String.valueOf(guessChars));
                 } else{
                     addScore(-1);
                     gameView.letterAbsent(letter);
                 }
            }

    private void addScore(int value) {
        user.addScore(value);
        userService.save(user);
    }
}
