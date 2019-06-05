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
                               @RequestParam(value = "sessionID", defaultValue = "not given") String sessionID) throws CorruptFrontendException {
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
                messageLeavingUser(sessionUserWantsToLeave, userToLeaveSession);
                for (int i = 0; i < sessionHost.getMessages().size(); i++) {
                    if (sessionHost.getMessages().get(i).getDescription().equals(hostMessage.getDescription())){
                        Message toBeDeleted = sessionHost.getMessages().get(i);
                        sessionHost.getMessages().remove(i);
                        messageRepository.delete(toBeDeleted);
                        log.info("Hosts Join Request messages deleted");
                        i--;
                    }
                }
            }
            if (tryToLeaveResultCode == 0) {
                messageHostAboutLeave(sessionUserWantsToLeave, userToLeaveSession, sessionHost);
                messageLeavingUser(sessionUserWantsToLeave, userToLeaveSession);
            }
            return (long) tryToLeaveResultCode;
        } else {
            log.info("Corrupt Frontend tried to access Backend");
            throw new CorruptFrontendException();
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
