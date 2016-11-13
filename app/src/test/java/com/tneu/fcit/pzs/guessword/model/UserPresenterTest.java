package com.tneu.fcit.pzs.guessword.model;

import com.tneu.fcit.pzs.guessword.helper.UserServiceStubImpl;
import com.tneu.fcit.pzs.guessword.presenter.UserPresenter;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserPresenterTest {
    private static UserPresenter userPresenter;
    private static InputStream stdin = System.in;

    @BeforeClass
    public static void setUp() {
        userPresenter = new UserPresenter(new UserServiceStubImpl());
    }

    private static void pipeToStdin(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    public void checkUserGenderUpdateLowercase() {
        final User user = new User("name", "pass");

        pipeToStdin("my new name\n"
                + "my new surname\n"
                + "1997\n"
                + "male");
        userPresenter.updateProfile(user);

        assertThat(user.getGender(), is(Gender.MALE));
    }

    // TODO Write more tests

    @After
    public void tearDown() {
        System.setIn(stdin);
    }
}
