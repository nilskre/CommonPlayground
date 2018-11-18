package com.wordpress.commonplayground.commonplayground;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Session {
    private Long id;
    private String title;
    private String description;
    private String game;
    private String place;
    private String date;
    private int numberOfPlayers;
    private Long idOfHost;
    private List<User> users = new ArrayList();


    public Session() {}

    public Session(String title, String description, String game, String place, String date, int numberOfPlayers) {
        this.title = title;
        this.description = description;
        this.game = game;
        this.place = place;
        this.date = date;
        this.numberOfPlayers = numberOfPlayers;
        this.id  = (long) 1; /*REMOVE THIS once get Sessions works*/
        users.add(new User("Host"));
        users.add(new User("User 2"));
        // users.size() == numberOfPlayers
    }

    public Long getId() {return id;}

    public String getTitle() {
        return title;
    }

    public String getGame() {
        return game;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Session title=" + title + " description=" + description + " game=" + game + " place=" + place + " date=" + date + " numberOfPlayers=" + numberOfPlayers + " users="; //+ users;
    }
}
