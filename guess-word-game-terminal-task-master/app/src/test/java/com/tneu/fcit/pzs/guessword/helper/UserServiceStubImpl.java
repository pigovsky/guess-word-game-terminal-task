package com.tneu.fcit.pzs.guessword.helper;

import com.tneu.fcit.pzs.guessword.model.User;
import com.tneu.fcit.pzs.guessword.service.UserService;

import java.util.Map;

/**
 * Created by yp on 03.11.16.
 */
public class UserServiceStubImpl implements UserService {
    public void save(User user) {

    }

    public Map<String, User> all() {
        return null;
    }

    public User check(String nick, String password) {
        return null;
    }
}
