package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private String game;
    private String place;
    private String date;
    private int numberOfPlayers;
    private Long idOfHost;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<User> users = new ArrayList();

    public Session() {}

    public Session(String title, String description, String game, String place, String date, int numberOfPlayers) {
        this.title = title;
        this.description = description;
        this.game = game;
        this.place = place;
        this.date = date;
        this.numberOfPlayers = numberOfPlayers;

        users.add(new User("My Name"));
        users.add(new User("Test User"));
        // users.size() == numberOfPlayers
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
        return "Session title=" + title + " description=" + description + " game=" + game + " place=" + place + " date=" + date + " numberOfPlayers=" + numberOfPlayers + " users="; //+ users;
    }
}