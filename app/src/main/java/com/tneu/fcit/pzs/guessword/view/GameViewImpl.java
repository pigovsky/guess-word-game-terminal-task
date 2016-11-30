package com.tneu.fcit.pzs.guessword.view;

import com.tneu.fcit.pzs.guessword.presenter.GamePresenter;
import com.tneu.fcit.pzs.guessword.service.GameTasksService;
import com.tneu.fcit.pzs.guessword.service.GameTasksServiceImpl;
import com.tneu.fcit.pzs.guessword.model.User;
import com.tneu.fcit.pzs.guessword.service.UserServiceImpl;
import com.tneu.fcit.pzs.guessword.utils.Utils;

/**
 * Created by yp on 02.11.16.
 */
public class GameViewImpl implements GameView {
    private final User user;
    private final UserServiceImpl userBase;
    private final GameTasksService gameTasksService;

    public GameViewImpl(User user) {
        this.user = user;
        userBase = new UserServiceImpl();
        gameTasksService = new GameTasksServiceImpl();
    }

    public void gameLoop() {
        GamePresenter game = initNewGame();
        while (true) {
            String guess = Utils.SCANNER.nextLine();
            if (guess.equalsIgnoreCase("exit")) {
                System.exit(0);
            } else if (guess.length() == 1) {
                game.checkLetter(guess);
            } else if (guess.length() > 1) {
                game.checkWord(guess);
                game = initNewGame();
            }
        }
    }

    @Override
    public void showCurrentUserGuess(String userCurrentGuess) {
        System.out.println(userCurrentGuess);
    }

    @Override
    public void letterAbsent(String letter) {
        System.err.printf("There is no %s letter in the secret word. Your score is %d\n", letter, user.getScore());
    }

    @Override
    public void letterHasBeenFound(String letter) {
        System.out.printf("Yes, secret word contains letter %s. Your score is %d\n", letter, user.getScore());
    }

    @Override
    public void showGameOver(String guess) {
        System.err.printf("It is not %s. You have loose ((. Your score is %d\n", guess, user.getScore());
    }

    @Override
    public void letterAlreadyFound(String letter) {
        System.out.printf("Already found this letter %s. Your score is %d\n", letter, user.getScore());
    }

    @Override
    public void showCongratulations(String secretWord) {
        System.out.printf("The word is really %s. You have win! Your score is %d\n", secretWord, user.getScore());
    }

    private GamePresenter initNewGame() {
        GamePresenter game = new GamePresenter(user, userBase, this, Utils.getRandomItem(gameTasksService.getTasks()));
        System.out.printf("Guess a word of %d letters\n", game.getSecretWord().length());
        showCurrentUserGuess(game.getUserCurrentGuess());
        return game;
    }
}
