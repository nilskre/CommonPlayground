package commonplayground.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private Long id;
    @Getter private String username;
    @Getter private String password;
    @Getter private String email;
    @Getter
    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @ToString.Exclude
    private List<Message> messages;

    public User() {
    }

    public User(String name,String password, String email) {
        this.username = name;
        this.email=email;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password= encoder.encode(password);

        this.messages  = new ArrayList();
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public void removeMessage(Message message){
        this.messages.remove(message);
    }
}