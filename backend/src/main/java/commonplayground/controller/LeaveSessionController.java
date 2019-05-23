package commonplayground.controller;

import commonplayground.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaveSessionController {

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
                               @RequestParam(value = "sessionID", defaultValue = "not given") String sessionID) {
        Long userIDAsLong = Long.parseLong(userID);
        Long sessionIDAsLong = Long.parseLong(sessionID);

        User userToLeaveSession = userRepository.findAllById(userIDAsLong);
        Session sessionUserWantsToLeave = sessionRepository.findAllById(sessionIDAsLong);
        User sessionHost = userRepository.findAllById(sessionUserWantsToLeave.getIdOfHost());

        int tryToLeaveResultCode = sessionUserWantsToLeave.removeUserFromSession(userToLeaveSession);
        sessionRepository.save(sessionUserWantsToLeave);
        if (tryToLeaveResultCode == 0) {
            messageHostAboutLeave(sessionUserWantsToLeave, userToLeaveSession, sessionHost);
            messageLeavingUser(sessionUserWantsToLeave, userToLeaveSession);
        }
        return (long) tryToLeaveResultCode;
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
