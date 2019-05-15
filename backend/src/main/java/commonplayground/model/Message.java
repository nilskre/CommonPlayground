package commonplayground.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Getter
    private String type;
    @Getter
    private String authorName;
    @Getter
    private String title;
    @Getter
    private String description;
    @Getter
    private Long userIdWhoWantsToJoin;
    @Getter
    private Long sessionIdUserWantsToJoin;
    @Getter
    private boolean seen;

    public Message() {
    }

    public Message(String title, String description, Long userIDWHoWantsToJoin, Long sessionIdUserWantsToJoin, String authorName) {
        this.type = "JoinRequest";
        this.authorName = authorName;
        this.title = title;
        this.description = description;
        this.userIdWhoWantsToJoin = userIDWHoWantsToJoin;
        this.sessionIdUserWantsToJoin = sessionIdUserWantsToJoin;
        this.seen = false;
    }

    public Message(String title, String description, String authorName) {
        this.type = "Info";
        this.title = title;
        this.description = description;
        this.authorName = authorName;
        this.seen = false;
    }

    public void messageSeen() {
        this.seen = true;
    }
}
