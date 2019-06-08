package commonplayground.controller.messages;

import commonplayground.Application;
import commonplayground.model.CorruptFrontendException;
import commonplayground.model.Message;
import commonplayground.model.User;
import commonplayground.model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HasNewMessagesController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private final UserRepository userRepository;

    @Autowired
    public HasNewMessagesController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/hasNewMessages")
    public boolean getMyMessages(@RequestParam(value = "userID", defaultValue = "not given") String userID) throws CorruptFrontendException {
        Long userIDAsLong = Long.parseLong(userID);
        if (userRepository.findAllById(userIDAsLong) != null) {
            User userWhoWantsToMessageStatus = userRepository.findAllById(userIDAsLong);

            boolean hasNewMessages = false;

            for (Message message : userWhoWantsToMessageStatus.getMessages()) {
                if (!message.isSeen()) {
                    hasNewMessages = true;
                    System.out.println("UNREAD MESSAGES");
                }
            }

            return hasNewMessages;
        } else {
            log.info("Corrupt Frontend tried to access Backend");
            throw new CorruptFrontendException();
        }
    }
}