package commonplayground.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;


@Entity
@NoArgsConstructor
@ToString
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
    private String isOnline;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @Getter
    @ToString.Exclude
    private List<User> users = new ArrayList<>();

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @Getter
    @ToString.Exclude
    private Set<User> userWantToJoin = new HashSet<>();

    public Session(String title, String description, String game, String place, String date, String time, int numberOfPlayers, Long idOfHost, String genre, String isOnline) {
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

    public int joinRequestToSession(User user) {
        if (sessionFull()) {
            return -10;
        } else if (userAlreadyJoined(user)) {
            return -11;
        } else if (joinRequestPending(user)) {
            return -12;
        } else {
            return 0;
        }
    }

    public int addUserToSession(User user) {
        if (sessionFull()) {
            return -10;
        } else if (userAlreadyJoined(user)) {
            return -11;
        } else {
            this.users.add(user);
            removeUserWantToJoin(user);
            return 0;
        }
    }

    private boolean sessionFull() {
        return users.size() >= numberOfPlayers;
    }

    private boolean userAlreadyJoined(User user) {
        return users.contains(user);
    }

    private boolean joinRequestPending(User user) {
        return userWantToJoin.contains(user);
    }

    public int removeUserFromSession(User userToLeaveSession) {
        if (userIsHost(userToLeaveSession)) {
            return -20;
        } else if (userWantToJoin.contains(userToLeaveSession)) {
            userWantToJoin.remove(userToLeaveSession);
            return 1;
        } else {
            this.users.remove(userToLeaveSession);
            return 0;
        }
    }

    private boolean userIsHost(User userToLeaveSession) {
        return Objects.equals(userToLeaveSession.getId(), this.idOfHost);
    }

    public boolean addUserWantToJoin(User user) {
        return this.userWantToJoin.add(user);
    }

    public void removeUserWantToJoin(User user) {
        this.userWantToJoin.remove(user);
    }
}