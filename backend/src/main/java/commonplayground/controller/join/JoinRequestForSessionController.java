package commonplayground.controller.join;

import commonplayground.Application;
import commonplayground.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinRequestForSessionController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
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
                                      @RequestParam(value = "sessionID", defaultValue = "not given") String sessionID) throws Exception {
        Long userIDAsLong = Long.parseLong(userID);
        if(userRepository.findAllById(userIDAsLong) != null) {
            Long sessionIDAsLong = Long.parseLong(sessionID);

            User userWhoWantsToJoinSession = userRepository.findAllById(userIDAsLong);
            Session sessionUserWantsToJoin = sessionRepository.findAllById(sessionIDAsLong);

            int validityCheck = sessionUserWantsToJoin.joinRequestToSession(userWhoWantsToJoinSession);

            System.out.println("IN JOIN RESPONSE: UserID " + userID + " sessionID " + sessionID + " Host: " + userRepository.findAllById(sessionUserWantsToJoin.getIdOfHost()).toString());

            if (validityCheck != 0) {
                return (long) validityCheck;
            } else {
                Message requestForJoinMessage = new Message("Join request for " + sessionUserWantsToJoin.getTitle(), userWhoWantsToJoinSession.getUsername() + " wants to join this session", userWhoWantsToJoinSession.getId(), sessionIDAsLong, userWhoWantsToJoinSession.getUsername());

                sessionUserWantsToJoin.addUserWantToJoin(userWhoWantsToJoinSession);
                User hostOfSession = userRepository.findAllById(sessionUserWantsToJoin.getIdOfHost());
                hostOfSession.addMessage(requestForJoinMessage);
                messageRepository.save(requestForJoinMessage);
                return (long) validityCheck;
            }
        } else {
            log.info("Corrupt Frontend tried to access Backend");
            throw new Exception("");
        }
    }
}
