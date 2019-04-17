package commonplayground.controller;

import commonplayground.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinRequestForSessionController {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public JoinRequestForSessionController(SessionRepository sessionRepository, UserRepository userRepository, MessageRepository messageRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @RequestMapping("/joinRequestForSession")
    public Long joinRequestForSession(@RequestParam(value = "userID", defaultValue = "not given") String userID,
                                      @RequestParam(value = "sessionID", defaultValue = "not given") String sessionID) {
        Long userIDAsLong = Long.parseLong(userID);
        Long sessionIDAsLong = Long.parseLong(sessionID);

        User userWhoWantsToJoinSession = userRepository.findAllById(userIDAsLong);
        Session sessionUserWantsToJoin = sessionRepository.findAllById(sessionIDAsLong);

        int validityCheck = sessionUserWantsToJoin.joinRequestToSession(userWhoWantsToJoinSession);

        if (validityCheck != 0) {
            return (long) validityCheck;
        } else {
            Message requestForJoinMessage = new Message("Join request for " + sessionUserWantsToJoin.getTitle(), userWhoWantsToJoinSession.getUsername() + " wants to join this session", userWhoWantsToJoinSession.getId(), sessionIDAsLong);

            User hostOfSession = userRepository.findAllById(sessionUserWantsToJoin.getIdOfHost());
            hostOfSession.addMessage(requestForJoinMessage);
            messageRepository.save(requestForJoinMessage);
            return (long) validityCheck;
        }
    }
}
