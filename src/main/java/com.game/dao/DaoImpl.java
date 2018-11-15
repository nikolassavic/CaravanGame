package com.game.dao;

import com.game.entity.Caravan;
import com.game.entity.Member;
import com.game.entity.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoImpl implements Dao {
    @Override
    public int newUser(User user) {
        return 0;
    }

    @Override
    public int loggedUser(User user) {
        return 0;
    }

    @Override
    public int startNewGame(User user, Caravan caravan, Member member) {
        
        return 0;
    }

    @Override
    public int saveGame(Caravan caravan, Member member) {
        return 0;
    }

    @Override
    public int startSavedGame(User user, Caravan caravan, Member member) {
        return 0;
    }
}