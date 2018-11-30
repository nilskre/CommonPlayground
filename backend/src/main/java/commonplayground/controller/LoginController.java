package commonplayground.controller;

import commonplayground.model.User;
import commonplayground.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private User userTriedToLogin;

    private final UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/login")
    public Long login(@RequestParam(value = "email", defaultValue = "not given") String email,
                      @RequestParam(value = "password", defaultValue = "not given") String triedPassword) {
        userTriedToLogin = userRepository.findAllByEmail(email);
        if (userExists(email) && passwordCorrect(triedPassword)){
            return userTriedToLogin.getId();
        } else {
            return (long) -1;
        }
    }

    private boolean userExists(String email) {
        return (userRepository.findAllByEmail(email) != null);
    }

    private boolean passwordCorrect(String triedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String correctPassword = userTriedToLogin.getPassword();
        return encoder.matches(triedPassword, correctPassword);
    }
}
