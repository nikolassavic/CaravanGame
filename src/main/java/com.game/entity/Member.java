package com.game.entity;

import javax.persistence.*;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int caravanId;
    private String memberFirst;
    private boolean isAliveFirst;
    private int sickLevelFirst;
    private String memberSecond;
    private boolean isAliveSecond;
    private int sickLevelSecond;
    private String memberThird;
    private boolean isAliveThird;
    private int sickLevelThird;
    private String memberFourth;
    private boolean isAliveFourth;
    private int sickLevelFourth;
    private String memberFifth;
    private boolean isAliveFifth;
    private int sickLevelFifth;

    public Member() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getCaravanId() {
        return caravanId;
    }
    public void setCaravanId(int caravanId) {
        this.caravanId = caravanId;
    }

    public String getMemberFirst() {
        return memberFirst;
    }
    public void setMemberFirst(String memberFirst) {
        this.memberFirst = memberFirst;
    }

    public boolean isAliveFirst() {
        return isAliveFirst;
    }
    public void setAliveFirst(boolean aliveFirst) {
        isAliveFirst = aliveFirst;
    }

    public int getSickLevelFirst() {
        return sickLevelFirst;
    }
    public void setSickLevelFirst(int sickLevelFirst) {
        this.sickLevelFirst = sickLevelFirst;
    }

    public String getMemberSecond() {
        return memberSecond;
    }
    public void setMemberSecond(String memberSecond) {
        this.memberSecond = memberSecond;
    }

    public boolean isAliveSecond() {
        return isAliveSecond;
    }
    public void setAliveSecond(boolean aliveSecond) {
        isAliveSecond = aliveSecond;
    }

    public int getSickLevelSecond() {
        return sickLevelSecond;
    }
    public void setSickLevelSecond(int sickLevelSecond) {
        this.sickLevelSecond = sickLevelSecond;
    }

    public String getMemberThird() {
        return memberThird;
    }
    public void setMemberThird(String memberThird) {
        this.memberThird = memberThird;
    }

    public boolean isAliveThird() {
        return isAliveThird;
    }
    public void setAliveThird(boolean aliveThird) {
        isAliveThird = aliveThird;
    }

    public int getSickLevelThird() {
        return sickLevelThird;
    }
    public void setSickLevelThird(int sickLevelThird) {
        this.sickLevelThird = sickLevelThird;
    }

    public String getMemberFourth() {
        return memberFourth;
    }
    public void setMemberFourth(String memberFourth) {
        this.memberFourth = memberFourth;
    }

    public boolean isAliveFourth() {
        return isAliveFourth;
    }
    public void setAliveFourth(boolean aliveFourth) {
        isAliveFourth = aliveFourth;
    }

    public int getSickLevelFourth() {
        return sickLevelFourth;
    }
    public void setSickLevelFourth(int sickLevelFourth) {
        this.sickLevelFourth = sickLevelFourth;
    }

    public String getMemberFifth() {
        return memberFifth;
    }
    public void setMemberFifth(String memberFifth) {
        this.memberFifth = memberFifth;
    }

    public boolean isAliveFifth() {
        return isAliveFifth;
    }
    public void setAliveFifth(boolean aliveFifth) {
        isAliveFifth = aliveFifth;
    }

    public int getSickLevelFifth() {
        return sickLevelFifth;
    }
    public void setSickLevelFifth(int sickLevelFifth) {
        this.sickLevelFifth = sickLevelFifth;
    }
}
