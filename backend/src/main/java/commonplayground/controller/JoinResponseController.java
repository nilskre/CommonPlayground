package commonplayground.controller;

import commonplayground.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinResponseController {

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
                                      @RequestParam(value = "joinAccepted", defaultValue = "false") boolean joinAccepted) {
        Long userIDAsLong = Long.parseLong(userID);
        User sessionHost = userRepository.findAllById(userIDAsLong);
        int messageIDAsInt = Integer.parseInt(messageID);

        Message relevantMessage = getRelevantMessage(sessionHost, messageIDAsInt);

        Long sessionIdUserWantsToJoin = relevantMessage.getSessionIdUserWantsToJoin();
        Session sessionUserWantsToJoin = sessionRepository.findAllById(sessionIdUserWantsToJoin);
        Long userWantsToJoin = relevantMessage.getUserIdWhoWantsToJoin();
        User userWhoWantsToJoinSession = userRepository.findAllById(userWantsToJoin);

        if (joinAccepted) {
            removeMessageFromHostsMessages(sessionHost, relevantMessage);
            joinPlayerToSession(sessionUserWantsToJoin, userWhoWantsToJoinSession);
            messageToUserThatJoinWasSuccessful(sessionUserWantsToJoin, userWhoWantsToJoinSession);
        } else if (!joinAccepted) {
            removeMessageFromHostsMessages(sessionHost, relevantMessage);
            messageToUserThatJoinWasRejected(sessionUserWantsToJoin, userWhoWantsToJoinSession);
        }
    }

    private void messageToUserThatJoinWasSuccessful(Session sessionUserWantsToJoin, User userWhoWantsToJoinSession) {
        Message joinSuccessful = new Message("Join successful", "Join to session " + sessionUserWantsToJoin.getTitle() + " was successful");
        userWhoWantsToJoinSession.addMessage(joinSuccessful);
        messageRepository.save(joinSuccessful);
    }

    private void messageToUserThatJoinWasRejected(Session sessionUserWantsToJoin, User userWhoWantsToJoinSession) {
        Message joinSuccessful = new Message("Join rejected", "Join to session " + sessionUserWantsToJoin.getTitle() + " was rejected by session host");
        userWhoWantsToJoinSession.addMessage(joinSuccessful);
        messageRepository.save(joinSuccessful);
    }

    private void removeMessageFromHostsMessages(User sessionHost, Message relevantMessage) {
        messageRepository.save(relevantMessage);
        sessionHost.removeMessage(relevantMessage);
        messageRepository.delete(relevantMessage);
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
