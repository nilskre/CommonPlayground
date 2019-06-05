package commonplayground.controller.messages;

import commonplayground.Application;
import commonplayground.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemoveMessagesController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public RemoveMessagesController(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @RequestMapping("/removeMessage")
    public void removeMessage(@RequestParam(value = "userID", defaultValue = "not given") String userID,
                              @RequestParam(value = "messageID", defaultValue = "not given") String messageID) throws CorruptFrontendException {
        Long userIDAsLong = Long.parseLong(userID);
        if (userRepository.findAllById(userIDAsLong) != null) {
            User user = userRepository.findAllById(userIDAsLong);
            int messageIDAsInt = Integer.parseInt(messageID);

            Message toBeDeleted = null;
            for (int i = 0; i < user.getMessages().size(); i++) {
                if (user.getMessages().get(i).getId() == messageIDAsInt) {
                    toBeDeleted = user.getMessages().get(i);
                    user.getMessages().remove(i);
                    i--;
                }
            }

            if (toBeDeleted != null) {
                messageRepository.delete(toBeDeleted);
            }
        } else {
            log.info("Corrupt Frontend tried to access Backend");
            throw new CorruptFrontendException();
        }
    }
}
