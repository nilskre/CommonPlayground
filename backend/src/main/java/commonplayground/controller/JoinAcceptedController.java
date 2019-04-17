package commonplayground.controller;

import commonplayground.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinAcceptedController {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public JoinAcceptedController(SessionRepository sessionRepository, UserRepository userRepository, MessageRepository messageRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @RequestMapping("/joinAccepted")
    public Long joinRequestForSession(@RequestParam(value = "userID", defaultValue = "not given") String userID,
                                      @RequestParam(value = "messageID", defaultValue = "not given") String messageID) {
        Long userIDAsLong = Long.parseLong(userID);
        User sessionHost = userRepository.findAllById(userIDAsLong);
        int messageIDAsInt = Integer.parseInt(messageID);

        Message relevantMessage = getRelevantMessage(sessionHost, messageIDAsInt);

        Long sessionIdUserWantsToJoin = relevantMessage.getSessionIdUserWantsToJoin();
        Session sessionUserWantsToJoin = sessionRepository.findAllById(sessionIdUserWantsToJoin);
        Long userWantsToJoin = relevantMessage.getUserIdWhoWantsToJoin();
        User userWhoWantsToJoinSession = userRepository.findAllById(userWantsToJoin);

        removeMessageFromHostsMessages(sessionHost, relevantMessage);
        Long returnCodeShouldBeZero = joinPlayerToSession(sessionUserWantsToJoin, userWhoWantsToJoinSession);
        messageToUserThatJoinWasSuccessful(sessionUserWantsToJoin, userWhoWantsToJoinSession);

        return returnCodeShouldBeZero;
    }

    private void messageToUserThatJoinWasSuccessful(Session sessionUserWantsToJoin, User userWhoWantsToJoinSession) {
        Message joinSuccessful = new Message("Join successful", "Join to session " + sessionUserWantsToJoin.getTitle() + " was successful");
        userWhoWantsToJoinSession.addMessage(joinSuccessful);
        messageRepository.save(joinSuccessful);
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
