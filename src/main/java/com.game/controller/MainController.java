package com.game.controller;

import com.game.dao.Dao;
import com.game.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller()
public class MainController {
    String[] isValidMsg = {"Error", "Validated mail", "Not Validated"};
    @Autowired
    private Dao dao;

    @GetMapping(value = "/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String loginPagee(@ModelAttribute("user") User user) {
        return "login";
    }

    @PostMapping(value = "/login")
    public String logged(User user) {
        String redirect;
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        user.setId(-1);
        int result = dao.loggedUser(user);
        System.out.println(isValidMsg[result]);
        if (result == 0) {
            redirect = "redirect:/login";
        } else if (result == 1) {
            redirect = "game";
        } else if (result == 2) {
            redirect = "game";
        } else {
            redirect = "404";
        }
        return redirect;
    }

    @GetMapping(value = "/reg")
    public String regPage(@ModelAttribute("user") User newUser) {
        return "registration";
    }

    @PostMapping(value = "/regregistration")
    String registation(User newUser) {
        String redirect;
        newUser.setPassword(DigestUtils.sha256Hex(newUser.getPassword()));
        int resulet = dao.newUser(newUser);
        if (resulet == 1) {
            redirect = "redirect:/login";
        } else
            redirect = "redirect:/reg";
        return redirect;
    }
}
