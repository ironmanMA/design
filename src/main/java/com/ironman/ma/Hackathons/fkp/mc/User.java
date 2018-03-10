package com.ironman.ma.Hackathons.fkp.mc;


import java.util.Date;
import java.util.UUID;

public class User {
    String userId;
    String userName;

    public User(String userName) {
        this.userName = userName;
        this.userId = new Date().getTime() + UUID.randomUUID().toString();
    }
}

