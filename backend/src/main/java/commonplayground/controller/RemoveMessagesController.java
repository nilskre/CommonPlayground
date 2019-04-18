package commonplayground.controller;

import commonplayground.model.Message;
import commonplayground.model.MessageRepository;
import commonplayground.model.User;
import commonplayground.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemoveMessagesController {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public RemoveMessagesController(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @RequestMapping("/removeMessage")
    public void removeMessage(@RequestParam(value = "userID", defaultValue = "not given") String userID,
                              @RequestParam(value = "messageID", defaultValue = "not given") String messageID) {
        Long userIDAsLong = Long.parseLong(userID);
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
    }
}
