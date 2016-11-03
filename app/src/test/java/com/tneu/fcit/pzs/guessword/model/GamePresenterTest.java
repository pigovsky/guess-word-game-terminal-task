package com.tneu.fcit.pzs.guessword.model;

import com.tneu.fcit.pzs.guessword.helper.GameViewStubImpl;
import com.tneu.fcit.pzs.guessword.helper.UserServiceStubImpl;
import com.tneu.fcit.pzs.guessword.presenter.GamePresenter;
import com.tneu.fcit.pzs.guessword.service.UserServiceTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yp on 02.11.16.
 */
public class GamePresenterTest {
    public static final String SECRET_WORD = "Community";
    public static final String START_GUESS = "*********";
    public static final String RIGHT_GUESS = SECRET_WORD.toLowerCase();
    public static final String WRONG_GUESS = "Cool";
    private GamePresenter game;

    @Before
    public void setUp() throws Exception {
        User user = new User(UserServiceTest.NICK, UserServiceTest.PASS);
        game = new GamePresenter(user, new UserServiceStubImpl(), new GameViewStubImpl(), SECRET_WORD);
    }

    @Test
    public void checkWordDecreasesScoreOnWrongWord() throws Exception {
        checkInitialConditions();
        game.checkWord(WRONG_GUESS);
        assertEquals("When user has entered wrong word, score must be decreased by -100", -100, game.getUser().getScore());
    }

    public void checkInitialConditions() {
        assertEquals("Before game started, score must be 0", 0, game.getUser().getScore());
        assertEquals("Before game started, current guess must be " + START_GUESS, START_GUESS, game.getUserCurrentGuess());
    }

    @Test
    public void checkWordIncreasesScoreOnRightWord() throws Exception {
        checkInitialConditions();
        game.checkWord(SECRET_WORD);
        assertEquals("When user has entered right word, score must be increased by 100", 100, game.getUser().getScore());
    }

    @Test
    public void checkWordIncreasesScoreOnRightLowercaseWord() throws Exception {
        checkInitialConditions();
        game.checkWord(RIGHT_GUESS);
        assertEquals("When user has entered right word, score must be increased by 100", 100, game.getUser().getScore());
    }

    @Test
    public void checkLetterIncreasesScoreAndOpensLetterOnRightLetter() throws Exception {
        checkInitialConditions();
        game.checkLetter("o");
        assertEquals("When user has entered right letter, his score must be increased by 1", 1, game.getUser().getScore());
        assertEquals("Right letter must be opened", "*o*******", game.getUserCurrentGuess());
    }

    @Test
    public void checkLetterIncreasesScoreAndOpensLetterOnRightUpperLetter() throws Exception {
        checkInitialConditions();
        game.checkLetter("O");
        assertEquals("When user has entered right letter, his score must be increased by 1", 1, game.getUser().getScore());
        assertEquals("Right letter must be opened", "*o*******", game.getUserCurrentGuess());
    }

    @Test
    public void checkLetterIncreasesScoreAndOpensLetterOnRightLowerLetter() throws Exception {
        checkInitialConditions();
        game.checkLetter("c");
        assertEquals("When user has entered right letter, his score must be increased by 1", 1, game.getUser().getScore());
        assertEquals("Right letter must be opened", "C********", game.getUserCurrentGuess());
    }

    @Test
    public void checkLetterIncreasesScoreAndOpensLetterOnRightDoubleLetter() throws Exception {
        checkInitialConditions();
        game.checkLetter("m");
        assertEquals("When user has entered right letter, his score must be increased by 1", 1, game.getUser().getScore());
        assertEquals("Right letter must be opened", "**mm*****", game.getUserCurrentGuess());
    }

    @Test
    public void checkLetterDecreasesScoreOnWrongLetter() throws Exception {
        checkInitialConditions();
        game.checkLetter("z");
        assertEquals("When user has entered wrong letter, his score must be decreased by 1", -1, game.getUser().getScore());
        assertEquals("Guess must not be changed", START_GUESS, game.getUserCurrentGuess());
    }

}