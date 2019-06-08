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
public class JoinResponseController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public JoinResponseController(SessionRepository sessionRepository, UserRepository userRepository, MessageRepository messageRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @RequestMapping("/joinResponse")
    public void joinRequestForSession(@RequestParam(value = "userID", defaultValue = "not given") String userID,
                                      @RequestParam(value = "messageID", defaultValue = "not given") String messageID,
                                      @RequestParam(value = "userIDToJoin", defaultValue = "not given") String userIDToJoin,
                                      @RequestParam(value = "joinAccepted", defaultValue = "false") boolean joinAccepted) throws CorruptFrontendException {
        Long userIDAsLong = Long.parseLong(userID);
        if (userRepository.findAllById(userIDAsLong) != null) {
            User sessionHost = userRepository.findAllById(userIDAsLong);
            int messageIDAsInt = Integer.parseInt(messageID);

            Message relevantMessage = getRelevantMessage(sessionHost, messageIDAsInt);

            Long sessionIdUserWantsToJoin = relevantMessage.getSessionIdUserWantsToJoin();
            Session sessionUserWantsToJoin = sessionRepository.findAllById(sessionIdUserWantsToJoin);
            Long userWantsToJoin = Long.parseLong(userIDToJoin); //relevantMessage.getUserIdWhoWantsToJoin();
            User userWhoWantsToJoinSession = userRepository.findAllById(userWantsToJoin);

            if (joinAccepted) {
                removeMessageFromHostsMessages(sessionHost, relevantMessage);
                System.out.println("Deleted Request Message for Host");
                joinPlayerToSession(sessionUserWantsToJoin, userWhoWantsToJoinSession);
                messageToUserThatJoinWasSuccessful(sessionUserWantsToJoin, userWhoWantsToJoinSession, sessionHost);
            } else if (!joinAccepted) {
                removeMessageFromHostsMessages(sessionHost, relevantMessage);
                System.out.println("DELTE HOST MESSAGE");
                messageToUserThatJoinWasRejected(sessionUserWantsToJoin, userWhoWantsToJoinSession, sessionHost);
            }
        } else {
            log.info("Corrupt Frontend tried to access Backend");
            throw new CorruptFrontendException();
        }

    }

    private void messageToUserThatJoinWasSuccessful(Session sessionUserWantsToJoin, User userWhoWantsToJoinSession, User sessionHost) {
        Message joinSuccessful = new Message("Join successful", "Join to session " + sessionUserWantsToJoin.getTitle() + " was successful", sessionHost.getUsername());
        userWhoWantsToJoinSession.addMessage(joinSuccessful);
        messageRepository.save(joinSuccessful);
    }

    private void messageToUserThatJoinWasRejected(Session sessionUserWantsToJoin, User userWhoWantsToJoinSession, User sessionHost) {
        Message joinRejected = new Message("Join rejected", "Join to session " + sessionUserWantsToJoin.getTitle() + " was rejected by session host", sessionHost.getUsername());
        userWhoWantsToJoinSession.addMessage(joinRejected);
        messageRepository.save(joinRejected);
    }

    private void removeMessageFromHostsMessages(User sessionHost, Message relevantMessage) {
        messageRepository.save(relevantMessage);
        sessionHost.removeMessage(relevantMessage);
    }

    private Long joinPlayerToSession(Session sessionUserWantsToJoin, User userWhoWantsToJoinSession) {
        int tryToJoinResultCode = sessionUserWantsToJoin.addUserToSession(userWhoWantsToJoinSession);
        sessionRepository.save(sessionUserWantsToJoin);
        return (long) tryToJoinResultCode;
    }

    private Message getRelevantMessage(User sessionHost, int messageIDAsInt) {
        Message relevantMessage = null;
        for (Message message : sessionHost.getMessages()) {
            if (message.getId() == messageIDAsInt) {
                relevantMessage = message;
            }
        }
        return relevantMessage;
    }
}
