package commonplayground.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@ToString
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

    public Message() {
    }

    public Message(String title, String description, Long userIDWHoWantsToJoin, Long sessionIdUserWantsToJoin, String authorName) {
        this.type = "JoinRequest";
        this.authorName = authorName;
        this.title = title;
        this.description = description;
        this.userIdWhoWantsToJoin = userIDWHoWantsToJoin;
        this.sessionIdUserWantsToJoin = sessionIdUserWantsToJoin;
    }

    public Message(String title, String description, String authorName) {
        this.type = "Info";
        this.title = title;
        this.description = description;
        this.authorName = authorName;
    }
}
