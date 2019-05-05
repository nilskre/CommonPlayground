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
    private String title;
    @Getter
    private String description;
    @Getter
    private Long userIdWhoWantsToJoin;
    @Getter
    private Long sessionIdUserWantsToJoin;

    public Message() {
    }

    public Message(String title, String description, Long userIDWHoWantsToJoin, Long sessionIdUserWantsToJoin) {
        this.type = "JoinRequest";
        this.title = title;
        this.description = description;
        this.userIdWhoWantsToJoin = userIDWHoWantsToJoin;
        this.sessionIdUserWantsToJoin = sessionIdUserWantsToJoin;
    }

    public Message(String title, String description) {
        this.type = "Info";
        this.title = title;
        this.description = description;
    }
}
