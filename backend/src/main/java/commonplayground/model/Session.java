package commonplayground.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Entity
public class Session {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter private Long id;
    @Getter private String title;
    @Getter private String description;
    @Getter private String game;
    @Getter private String place;
    @Getter private String date;
    @Getter private String time;
    @Getter private int numberOfPlayers;
    @Getter private Long idOfHost;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @Getter @ToString.Exclude private List<User> users = new ArrayList();

    public Session() {}

    public Session(String title, String description, String game, String place, String date, String time, int numberOfPlayers, Long idOfHost) {
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

}