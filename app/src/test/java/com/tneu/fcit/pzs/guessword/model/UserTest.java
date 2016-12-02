package com.tneu.fcit.pzs.guessword.model;


        import com.tneu.fcit.pzs.guessword.helper.GameViewStubImpl;
        import com.tneu.fcit.pzs.guessword.helper.UserServiceStubImpl;
        import com.tneu.fcit.pzs.guessword.presenter.GamePresenter;
        import com.tneu.fcit.pzs.guessword.service.UserService;
        import com.tneu.fcit.pzs.guessword.service.UserServiceTest;
        import org.junit.Before;
        import org.junit.Test;

        import java.util.Date;
        import static org.junit.Assert.*;

public class UserTest {
    public static final String NICK = "fedirrr";
    public static final String PASSWORD = "password1!";
    public static final String NAME = "Fedir";
    public static final String SURNAME = "Kiyko";
    public static final String SEX = "Male";
    public static final Date BIRTH = new Date(1996,9,3);
    User user;

    @org.junit.Before
    public void setUp() throws Exception
    {
        user = new User(NICK , PASSWORD, NAME, SURNAME, BIRTH , SEX);
    }

    @Test
    public void checkWordDecreasesScoreOnWrongWord() throws Exception {

        assertEquals("fedirrr",user.getNick());
        assertEquals(user.getPassword(),user.getPassword());
        assertEquals(user.getName(), user.getName());
        assertEquals(user.getSurname(), user.getSurname());
        assertEquals(user.getSex(), user.getSex());
        assertEquals(user.getBirth(), user.getBirth());
    }
}
