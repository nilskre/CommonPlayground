package commonplayground.controller.messages;

import commonplayground.Application;
import commonplayground.model.Message;
import commonplayground.model.User;
import commonplayground.model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetMessagesController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private final UserRepository userRepository;

    @Autowired
    public GetMessagesController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/getMyMessages")
    public List<Message> getMyMessages(@RequestParam(value = "userID", defaultValue = "not given") String userID) throws Exception {
        Long userIDAsLong = Long.parseLong(userID);
        if (userRepository.findAllById(userIDAsLong) != null) {
            User userWhoWantsToGetMessages = userRepository.findAllById(userIDAsLong);

            return userWhoWantsToGetMessages.getMessages();
        } else {
            log.info("Corrupt Frontend tried to access Backend");
            throw new Exception("");
        }
    }
}
