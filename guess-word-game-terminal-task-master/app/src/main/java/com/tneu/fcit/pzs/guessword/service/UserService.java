package com.tneu.fcit.pzs.guessword.service;

import com.tneu.fcit.pzs.guessword.model.User;

import java.util.Map;

/**
 * Created by yp on 03.11.16.
 */
public interface UserService {
    void save(User user);

    Map<String, User> all();

    User check(String nick, String password);
}
