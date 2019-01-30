package com.game.controller;

import com.game.dao.Dao;
import com.game.entity.Data;
import com.game.entity.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private Dao dao;

    @GetMapping(value = "/game-setup/new/{id}", produces = "application/json")
    public List newG(@PathVariable int id) {
        User user = new User();
        user.setId(id);
        List<Object> data = dao.startNewGame(user);
        data.remove(0);
        return data;
    }

    @GetMapping(value = "/game-setup/load/{id}", produces = "application/json")
    public List loadG(@PathVariable int id) {
        User user = new User();
        user.setId(id);
        List<Object> data = dao.startSavedGame(user);
        data.remove(0);
        return data;
    }

    @PostMapping(value = "/game-setup/save/{id}", produces = "application/json")
    public String saveG(@PathVariable int id, @RequestBody String gotData) {
        Gson gson = new Gson();
        User user = new User();
        user.setId(id);
        String response;
        Data data = gson.fromJson(gotData, Data.class);
        int res = dao.saveGame(user, data.getCaravan(), data.getMember());
        if (res == 1) {
            response = "{\"msg\" : \"GOOD\"}";
        } else if (res == -1) {
            response = "{\"msg\" : \"NOT GOOD\"}";
        } else {
            response = "{\"msg\" : \"VERY NOT GOOD =)\"}";
        }
        return response;
    }
}