package com.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller()
public class GameController {

    @GetMapping(value = "/game")
    public String game(HttpServletRequest request, HttpServletResponse response){
        String httpSessionId = request.getRequestedSessionId();
        System.out.println("SESSION ID FROM REQUEST FOR SESSION-ID>>> "+httpSessionId);
        return "game";
    }
}
