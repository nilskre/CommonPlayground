package commonplayground.controller;

import commonplayground.model.Message;
import commonplayground.model.User;
import commonplayground.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessagesController {

    private final UserRepository userRepository;

    @Autowired
    public MessagesController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/getMyMessages")
    public List<Message> getMyMessages(@RequestParam(value = "userID", defaultValue = "not given") String userID) {
        Long userIDAsLong = Long.parseLong(userID);

        User userWhoWantsToGetMessages = userRepository.findAllById(userIDAsLong);
        
        return userWhoWantsToGetMessages.getMessages();
    }
}
