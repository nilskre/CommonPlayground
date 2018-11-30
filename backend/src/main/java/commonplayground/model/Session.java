package commonplayground.model;

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
    private String time;
    private int numberOfPlayers;
    private Long idOfHost;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<User> users = new ArrayList();

    public Session() {}

    public Session(String title, String description, String game, String place, String date, int numberOfPlayers, String time, Long idOfHost) {
        this.title = title;
        this.description = description;
        this.game = game;
        this.place = place;
        this.date = date;
        this.time = time;
        this.numberOfPlayers = numberOfPlayers;
        this.idOfHost = idOfHost;

        // users.size() == numberOfPlayers
    }

    public void addUserToSession(User user){
        this.users.add(user);
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

    public String getTime() {
        return time;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Session title=" + title + " description=" + description + " game=" + game + " place=" + place + " date=" + date + " numberOfPlayers=" + numberOfPlayers + " users="; //+ users;
    }
}