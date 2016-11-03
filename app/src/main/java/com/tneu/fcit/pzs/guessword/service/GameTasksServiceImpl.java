package com.tneu.fcit.pzs.guessword.service;

/**
 * Created by yp on 02.11.16.
 */
public class GameTasksServiceImpl implements GameTasksService {
    @Override
    public String[] getTasks() {
        return new String[]{
                "Stepan",
                "Viktor",
                "Nastia",
                "Chrystia",
                "Natalia",
                "Ksenia"
        };
    }
}
