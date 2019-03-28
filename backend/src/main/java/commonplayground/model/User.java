package commonplayground.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private Long id;
    @Getter private String username;
    @Getter private String password;
    @Getter private String email;

    public User() {
    }

    public User(String name,String password, String email) {
        this.username = name;
        this.email=email;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password= encoder.encode(password);
    }
}