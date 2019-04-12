package commonplayground.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Getter
    private String title;
    @Getter
    private String description;
    @Getter
    private String game;
    @Getter
    private String place;
    @Getter
    private String date;
    @Getter
    private String time;
    @Getter
    private int numberOfPlayers;
    @Getter
    private Long idOfHost;
    @Getter
    private String genre;
    @Getter
    private boolean isOnline;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @Getter
    @ToString.Exclude
    private List<User> users = new ArrayList();

    public Session() {
    }

    public Session(String title, String description, String game, String place, String date, String time, int numberOfPlayers, Long idOfHost, String genre, Boolean isOnline) {
        this.title = title;
        this.description = description;
        this.game = game;
        this.place = place;
        this.date = date;
        this.time = time;
        this.numberOfPlayers = numberOfPlayers;
        this.idOfHost = idOfHost;
        this.genre = genre;
        this.isOnline = isOnline;
    }

    public int addUserToSession(User user) {
        if (SessionFull()) {
            return -10;
        } else if (userAlreadyJoined(user)) {
            return -11;
        } else {
            this.users.add(user);
            return 0;
        }
    }

    private boolean userAlreadyJoined(User user) {
        return users.contains(user);
    }

    private boolean SessionFull() {
        return users.size() >= numberOfPlayers;
    }

    public int removeUserFromSession(User userToLeaveSession) {
        if (userIsHost(userToLeaveSession)) {
            return -20;
        } else {
            this.users.remove(userToLeaveSession);
            return 0;
        }
    }

    private boolean userIsHost(User userToLeaveSession) {
        return Objects.equals(userToLeaveSession.getId(), this.idOfHost);
    }
}