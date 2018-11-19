package com.game.controller;

import com.game.dao.Dao;
import com.game.dao.DaoImpl;
import com.game.entity.Caravan;
import com.game.entity.Member;
import com.game.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.List;

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
    public String loginPage(@ModelAttribute("user") User user) {
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
            redirect = "validate";
        } else {
            redirect = "redirect:/404";
        }
        return redirect;
    }

    @GetMapping(value = "/reg")
    public String regPage(@ModelAttribute("user") User newUser) {
        return "registration";
    }

    @PostMapping(value = "/registration")
    String registation(User newUser) {
        String redirect;
        newUser.setPassword(DigestUtils.sha256Hex(newUser.getPassword()));
        int result = dao.newUser(newUser);
        if (result == 1) {
            redirect = "login";
        } else if (result == 0) {
            redirect = "redirect:/reg";
        }else {
            redirect = "redirect:/404";
        }
        return redirect;
    }
}
