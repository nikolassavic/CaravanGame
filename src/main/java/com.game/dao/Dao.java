package com.game.dao;

import com.game.entity.Caravan;
import com.game.entity.Member;
import com.game.entity.User;

import java.util.List;

public interface Dao {
    public int newUser(User newUser);
    public int loggedUser(User user);
    public int validateUser(User user);
    public List<Object> startNewGame(User user);
    public List<Object> startSavedGame(User user);
    public int saveGame(User user, Caravan caravan, Member member);
    public boolean check(User user);
}