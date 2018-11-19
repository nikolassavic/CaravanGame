package com.game.dao;

import com.game.entity.Caravan;
import com.game.entity.Member;
import com.game.entity.User;

public interface Dao {
    public int newUser(User newUser);
    public int loggedUser(User user);
    public int validateUser(User user);
    public int startNewGame(User user, Caravan caravan, Member member);
    public int saveGame(Caravan caravan, Member member);
    public int startSavedGame(User user, Caravan caravan, Member member);
}