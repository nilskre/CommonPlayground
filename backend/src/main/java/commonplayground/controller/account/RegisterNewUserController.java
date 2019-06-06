package commonplayground.controller.account;

import commonplayground.model.User;
import commonplayground.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterNewUserController {

    private final UserRepository userRepository;

    @Autowired
    public RegisterNewUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/registerNewUser")
    public int registerNewUser(@RequestParam(value = "username", defaultValue = "not given") String username,
                               @RequestParam(value = "password", defaultValue = "not given") String password,
                               @RequestParam(value = "email", defaultValue = "not given") String email) {
        User user = new User(username, password, email);
        
        if (userRepository.findByEmail(email) != null) {
            return -3;
        }
        if (userRepository.findByUsername(username) != null) {
            return -2;
        } else {
            userRepository.save(user);
            return 0;
        }
    }
}
