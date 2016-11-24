package com.tneu.fcit.pzs.guessword.service;

import com.tneu.fcit.pzs.guessword.model.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yp on 02.11.16.
 */
public class UserServiceImpl implements UserService {

    public static final String USER_DB = "USER_DB";

    @Override
    public void save(User user) {
        Map<String, User> userMap = all();
        userMap.put(user.getNick(), user);
        try {
            new ObjectOutputStream(new FileOutputStream(USER_DB))
                    .writeObject(userMap);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public Map<String, User> all() {
        Map<String, User> userMap;
        try {
            userMap = (Map<String, User>) new ObjectInputStream(new FileInputStream(USER_DB))
                .readObject();
        } catch (Exception e) {
            userMap = new HashMap<>();
        }
        return userMap;
    }

    @Override
    public User check(String nick, String password) {
        User user = all().get(nick);
        return user != null && password.equals(user.getPassword()) ? user : null;
    }
}
