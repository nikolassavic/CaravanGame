package com.game.controller;

import com.game.dao.Dao;
import com.game.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller()
public class MainController {

    @Autowired
    private Dao dao;

    @GetMapping(value = "/")
    public String index(){
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String loginPagee(@ModelAttribute("user")User user){
        return "login";
    }

    @PostMapping(value = "/login")
    public String logged(User user){
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        int result = dao.loggedUser(user);
        System.out.println(result);
        return "game";
    }

    @GetMapping(value = "/reg")
    public String regPage(@ModelAttribute("user")User newUser){
        return "registration";
    }
    @PostMapping(value = "/regregistration")
    String registation (User newUser){
        String redirect;
        newUser.setPassword(DigestUtils.sha256Hex(newUser.getPassword()));
        int resulet = dao.newUser(newUser);
        System.out.println("success: 1/ error: 0 ::: " + resulet);
        if ( resulet == 1){
            redirect = "redirect:/login";
        }else
            redirect = "redirect:/reg";
        return redirect;
    }
}
