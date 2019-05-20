package commonplayground.controller;

import commonplayground.model.Message;
import commonplayground.model.User;
import commonplayground.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HasNewMessages {

    private final UserRepository userRepository;

    @Autowired
    public HasNewMessages(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/hasNewMessages")
    public boolean getMyMessages(@RequestParam(value = "userID", defaultValue = "not given") String userID) {
        Long userIDAsLong = Long.parseLong(userID);

        User userWhoWantsToMessageStatus = userRepository.findAllById(userIDAsLong);

        boolean hasNewMessages = false;

        for (Message message : userWhoWantsToMessageStatus.getMessages()) {
            if (!message.isSeen()) {
                hasNewMessages = true;
                System.out.println("UNREAD MESSAGES");
            }
        }

        return hasNewMessages;
    }
}