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
public class SendCommentController {

    private UserRepository userRepository;
    private MessageRepository messageRepository;
    private int status;

    @Autowired
    public SendCommentController(UserRepository userRepository, MessageRepository messageRepository) {
         this.userRepository = userRepository;
         this.messageRepository = messageRepository;
    }

    @RequestMapping("/sendComment")
    public int registerNewUser(@RequestParam(value = "userID", defaultValue = "not given") String userID,
                               @RequestParam(value = "receiverName", defaultValue = "not given") String receiverName,
                               @RequestParam(value = "messageTitle", defaultValue = "not given") String messageTitle,
                               @RequestParam(value = "messageContent", defaultValue = "not given")String messageContent){

        Message message = new Message(messageTitle, messageContent, userID);
        User receiver = userRepository.findByUsername(receiverName);

        if(receiver != null){
            status= 200;
            receiver.addMessage(message);
            messageRepository.save(message);
        }
        else if (receiver == null){
            status= -300;
        }
        return status;
    }
}
