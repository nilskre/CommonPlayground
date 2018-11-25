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
    public Long postNewSession(@RequestParam(value = "username", defaultValue = "not given") String username,
                               @RequestParam(value = "password", defaultValue = "not given") String triedPassword) {
        userTriedToLogin = userRepository.findAllByName(username);
        if (userExists(username) && passwordCorrect(triedPassword)){
            return userTriedToLogin.getId();
        } else {
            return (long) -1;
        }
    }

    private boolean userExists(String username) {
        return userRepository.findAllByName(username) != null;
    }

    private boolean passwordCorrect(String triedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String correctPassword = userTriedToLogin.getPassword();
        return encoder.matches(triedPassword, correctPassword);
    }
}
