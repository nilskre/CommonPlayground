package commonplayground.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = {"password"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Getter
    private String username;
    @Getter
    @ToString.Exclude
    private String password;
    @Getter
    private String email;
    @Getter
    @OneToMany(
            cascade = CascadeType.ALL
    )
    @ToString.Exclude
    private List<Message> messages;

    public User(String name, String password, String email) {
        this.username = name;
        this.email = email;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);

        this.messages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public void removeMessage(Message message) {
        this.messages.remove(message);
        System.out.println("Messages in modelclass " + messages);
    }
}