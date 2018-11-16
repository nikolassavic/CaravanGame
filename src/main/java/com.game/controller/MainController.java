package com.game.controller;

import com.game.dao.Dao;
import com.game.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
public class MainController {

    @Autowired
    private Dao dao;

    @RequestMapping(value = "/")
    public String loginPage(@ModelAttribute("user")User user){
        return "login";
    }

    @PostMapping(value = "/login")
    public String logged(User user){
        String sha256hex = DigestUtils.sha256Hex(user.getPassword());
        System.out.println(sha256hex);
        return "game";
    }

    @RequestMapping(value = "/reg")
    public String regPage(@ModelAttribute("user")User newUser){
        return "registration";
    }
    @PostMapping(value = "/regregistration")
    String registation (User newUser){
        newUser.setPassword(DigestUtils.sha256Hex(newUser.getPassword()));
        int resulet = dao.newUser(newUser);
        System.out.println("success: 1/ error: 0 ::: " + resulet);
        return "redirect:/";
    }
}
