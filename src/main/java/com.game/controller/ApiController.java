package com.game.controller;

import com.game.dao.Dao;
import com.game.dao.DaoImpl;
import com.game.entity.Caravan;
import com.game.entity.Member;
import com.game.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private Dao dao;

    @GetMapping(value = "/game-setup", produces = "application/json")
    public List setGame(){
        User user = new User();
        user.setId(2);
        List<Object> data = dao.startNewGame(user);
        data.remove(0);
        return data;
    }
}
