package hello;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private String title;
    private String description;
    private String game;
    private String place;
    private String date;
    private int numberOfPlayers;
    private List players  = new ArrayList();

    public Session(String title, String description, String game, String place, String date, int numberOfPlayers) {
        this.title = title;
        this.description = description;
        this.game = game;
        this.place = place;
        this.date = date;
        this.numberOfPlayers = numberOfPlayers;

        players.add("Host");
        // players.size() == numberOfPlayers
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

    public List getPlayers() {
        return players;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Session title=" + title + " game=" + game + " place=" + place + " date=" + date + " numberOfPlayers=" + numberOfPlayers + " players=" + players;
    }
}