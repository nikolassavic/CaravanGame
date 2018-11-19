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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller()
public class MainController {

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
    public String logged(User user, HttpServletRequest request, HttpServletResponse response) {
        String redirect;
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        user.setId(-1);
        int result = dao.loggedUser(user);
        if (result == 0) {
            redirect = "redirect:/login";
        } else if (result == 1) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("thisUser", user);
            System.out.println("SESSION ID FROM HTTPSESION >>> "+httpSession.getId());
            redirect = "redirect:/game";
        } else if (result == 2) {
            redirect = "redirect:/validate";
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

    @GetMapping("/validate")
    public String validate(){
        return "validate";
    }
    @PostMapping("/validated")
    public String validated( HttpServletRequest request, HttpSession session){
        String redirect;
        if (request.getParameter("label") == request.getParameter("user-input")){
            User user = (User) session.getAttribute("thisUser");
            dao.validateUser(user);
            redirect = "redirect:/game";
        }
        else {
            redirect = "redirect:/validate";
        }
        return redirect;
    }

    @GetMapping(value = "/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("/login");
    }
}