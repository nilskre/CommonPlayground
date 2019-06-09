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
public class SendCommentController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private UserRepository userRepository;
    private MessageRepository messageRepository;
    private int status;

    @Autowired
    public SendCommentController(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @RequestMapping("/sendComment")
    public int sendComment(@RequestParam(value = "userID", defaultValue = "not given") String userID,
                           @RequestParam(value = "receiverName", defaultValue = "not given") String receiverName,
                           @RequestParam(value = "messageTitle", defaultValue = "not given") String messageTitle,
                           @RequestParam(value = "messageContent", defaultValue = "not given") String messageContent) throws CorruptFrontendException {
        Long userIDAsLong = Long.parseLong(userID);
        if (userRepository.findAllById(userIDAsLong) != null) {
            Message message = new Message(messageTitle, messageContent, userID);
            User receiver = userRepository.findByUsername(receiverName);

            if (receiver != null) {
                status = 200;
                receiver.addMessage(message);
                messageRepository.save(message);
            } else if (receiver == null) {
                status = -300;
            }
            return status;
        } else {
            log.info("Corrupt Frontend tried to access Backend");
            throw new CorruptFrontendException();
        }
    }
}
