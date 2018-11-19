package com.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
public class GameController {

    @RequestMapping(value = "/gameSetup")
    public String gameSetupPage(){
        return "game-setup";
    }

    @RequestMapping(value = "/game")
    public String game(){
        return "game";
    }
}
