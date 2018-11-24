package com.wordpress.commonplayground;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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


    public Session() {
    }

    public Session(String title, String description, String game, String place, String date, int numberOfPlayers, Long sessionId) {
        this.title = title;
        this.description = description;
        this.game = game;
        this.place = place;
        this.date = date;
        this.numberOfPlayers = numberOfPlayers;
        this.id = sessionId; /*REMOVE THIS once id can be passed*/
        // users.size() == numberOfPlayers
    }

    public static Session parseSession(JSONObject sessionObject) {
        List<User> users = new ArrayList();
        try {
            JSONArray parsedUsers = sessionObject.getJSONArray("users");
            for (int i = 0; i < parsedUsers.length(); i++) {
                users.add(new User(parsedUsers.getJSONObject(i).getString("name")));
            }
            Session parsed = new Session(sessionObject.getString("title"), sessionObject.getString("description"), sessionObject.getString("game"), sessionObject.getString("place"), sessionObject.getString("date"), sessionObject.getInt("numberOfPlayers"), sessionObject.getLong("id"));
            parsed.users = users;
            Log.v("PARSED", String.valueOf("ID: " + parsed.getId()));
            return parsed;

        } catch (JSONException e) {
            Log.d("Parse.Session", e.toString());
        }
        return null;
    }

    public Long getId() {
        return id;
    }

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
        return "Session id=" + id + "Session title=" + title + " description=" + description + " game=" + game + " place=" + place + " date=" + date + " numberOfPlayers=" + numberOfPlayers + " users="; //+ users;
    }
}
