package com.game.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "caravans")
public class Caravan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private int toGoal;
    private int money;
    private int food;
    private int medicine;
    private int ammo;
    private int ox;
    private int canCarry;
    private Date lastSaved;

    public Caravan() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getToGoal() {
        return toGoal;
    }
    public void setToGoal(int toGoal) {
        this.toGoal = toGoal;
    }

    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }

    public int getFood() {
        return food;
    }
    public void setFood(int food) {
        this.food = food;
    }

    public int getMedicine() {
        return medicine;
    }
    public void setMedicine(int medicine) {
        this.medicine = medicine;
    }

    public int getAmmo() {
        return ammo;
    }
    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getOx() {
        return ox;
    }
    public void setOx(int ox) {
        this.ox = ox;
    }

    public int getCanCarry() {
        return canCarry;
    }
    public void setCanCarry(int canCarry) {
        this.canCarry = canCarry;
    }

    public Date getLastSaved() {
        return lastSaved;
    }
    public void setLastSaved(Date lastSaved) {
        this.lastSaved = lastSaved;
    }
}
