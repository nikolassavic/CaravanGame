package com.game.controller;

import com.game.dao.DaoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
public class MainController {

    @RequestMapping(value = "/")
    public String homePage(){
        return "login";
    }
}
