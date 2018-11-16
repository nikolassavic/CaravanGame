package com.game.controller;

import com.game.dao.Dao;
import com.game.dao.DaoImpl;
import com.game.entity.Caravan;
import com.game.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
public class MainController {

    @Autowired
    Dao dao;

    @RequestMapping(value = "/")
    public String homePage(){
        Caravan caravan = new Caravan();
        Member member = new Member();
        dao.saveGame(caravan, member);
        return "login";
    }
}
