package commonplayground.controller;

import commonplayground.model.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class SendCommentController {

    private MessageRepository messageRepository;

    @Autowired
    public SendCommentController(MessageRepository messageRepository) {
         this.messageRepository = messageRepository;
    }

    @RequestMapping("/sendComment")
    public int registerNewUser(@RequestParam(value = "userID", defaultValue = "not given") String userID,
                               @RequestParam(value = "userName", defaultValue = "not given") String userName,
                               @RequestParam(value = "messageTitle", defaultValue = "not given") String messageTitle,
                               @RequestParam(value = "messageContent", defaultValue = "not given")String messageContent){



    }
}
