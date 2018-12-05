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
    private boolean userExists;
    private boolean passwordCorrect;

    private final UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/login")
    public Long login(@RequestParam(value = "email", defaultValue = "not given") String email,
                      @RequestParam(value = "password", defaultValue = "not given") String triedPassword) {
        if (userRepository.findAllByEmail(email) != null){
            userTriedToLogin = userRepository.findAllByEmail(email);
        }
        userExists = userExists(email);
        passwordCorrect = passwordCorrect(triedPassword);

        return generateResponse();
    }

    private Long generateResponse() {
        if (!userExists){
            return (long) -4;
        } else if(userExists && !passwordCorrect){
            return (long) -5;
        } else if (userExists && passwordCorrect){
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
