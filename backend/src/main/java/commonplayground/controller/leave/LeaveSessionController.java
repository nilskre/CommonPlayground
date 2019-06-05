package commonplayground.controller.leave;

import commonplayground.Application;
import commonplayground.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaveSessionController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public LeaveSessionController(SessionRepository sessionRepository, UserRepository userRepository, MessageRepository messageRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @RequestMapping("/leaveSession")
    public Long leaveSession(@RequestParam(value = "userID", defaultValue = "not given") String userID,
                               @RequestParam(value = "sessionID", defaultValue = "not given") String sessionID) throws Exception {
        Long userIDAsLong = Long.parseLong(userID);
        if (userRepository.findAllById(userIDAsLong) != null) {
            Long sessionIDAsLong = Long.parseLong(sessionID);

            User userToLeaveSession = userRepository.findAllById(userIDAsLong);
            Session sessionUserWantsToLeave = sessionRepository.findAllById(sessionIDAsLong);
            User sessionHost = userRepository.findAllById(sessionUserWantsToLeave.getIdOfHost());

            int tryToLeaveResultCode = sessionUserWantsToLeave.removeUserFromSession(userToLeaveSession);
            sessionRepository.save(sessionUserWantsToLeave);
            Message hostMessage = new Message("Join request for " + sessionUserWantsToLeave.getTitle(), userToLeaveSession.getUsername() + " wants to join this session", userToLeaveSession.getId(), sessionIDAsLong, userToLeaveSession.getUsername());
            if (tryToLeaveResultCode == 1) {
                //for (Message message:sessionHost.getMessages()) {
                //    System.out.println("\n");
                //    System.out.println("Messages " + message.getDescription());
                //    System.out.println("Messages origin " + hostMessage.getDescription());
                //    System.out.println("Condition " + message.getDescription().equals(hostMessage.getDescription()));
                //    if (message.getDescription().equals(hostMessage.getDescription())){
                //        sessionHost.removeMessage(message);
                //        //messageRepository.delete(message);
                //        //messageRepository.deleteById(message.getId());
                //        System.out.println("Message deleted");
                //    }
                //}

                //messageRepository.delete(hostMessage);

                //messageRepository.delete(messageRepository.findAllByDescription(hostMessage.getDescription()));
                //messageHostAboutLeave(sessionUserWantsToLeave, userToLeaveSession, sessionHost);
                messageLeavingUser(sessionUserWantsToLeave, userToLeaveSession);
                for (int i = 0; i < sessionHost.getMessages().size(); i++) {
                    System.out.println("\n");
                    System.out.println("Messages " + sessionHost.getMessages().get(i).getDescription());
                    System.out.println("Messages origin " + hostMessage.getDescription());
                    System.out.println("Condition " + sessionHost.getMessages().get(i).getDescription().equals(hostMessage.getDescription()));
                    if (sessionHost.getMessages().get(i).getDescription().equals(hostMessage.getDescription())){
                        System.out.println("\n Session Host Messages in for before del" + sessionHost.getMessages());
                        Message toBeDeleted = sessionHost.getMessages().get(i);
                        sessionHost.getMessages().remove(i);
                        messageRepository.delete(toBeDeleted);
                        //sessionHost.removeMessage(sessionHost.getMessages().get(i));
                        System.out.println("\n Session Host Messages in for after del " + sessionHost.getMessages());
                        System.out.println("Message deleted");
                        i--;
                    }
                }
            }
            System.out.println("\n Between messages " + sessionHost.getMessages());
            System.out.println("All messages " + messageRepository.findAll());
            if (tryToLeaveResultCode == 0) {
                messageHostAboutLeave(sessionUserWantsToLeave, userToLeaveSession, sessionHost);
                messageLeavingUser(sessionUserWantsToLeave, userToLeaveSession);
            }
            return (long) tryToLeaveResultCode;
        } else {
            log.info("Corrupt Frontend tried to access Backend");
            throw new Exception("");
        }

    }

    private void messageHostAboutLeave(Session sessionUserWantsToLeave, User userWhoWantsToLeaveSession, User sessionHost) {
        Message userLeftSession = new Message("User left session", "User " + userWhoWantsToLeaveSession.getUsername() + " has left ", sessionUserWantsToLeave.getTitle());
        sessionHost.addMessage(userLeftSession);
        messageRepository.save(userLeftSession);
    }

    private void messageLeavingUser(Session sessionUserWantsToLeave, User userWhoWantsToLeaveSession) {
        Message userLeftSession = new Message("Left successful", "You left " + sessionUserWantsToLeave.getTitle(), "CommonPlayground");
        userWhoWantsToLeaveSession.addMessage(userLeftSession);
        messageRepository.save(userLeftSession);
    }
}
