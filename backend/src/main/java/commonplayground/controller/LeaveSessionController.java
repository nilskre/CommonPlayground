package commonplayground.controller;

import commonplayground.model.Session;
import commonplayground.model.SessionRepository;
import commonplayground.model.User;
import commonplayground.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaveSessionController {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    @Autowired
    public LeaveSessionController(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/leaveSession")
    public Long postNewSession(@RequestParam(value = "userID", defaultValue = "not given") String userID,
                               @RequestParam(value = "sessionID", defaultValue = "not given") String sessionID) {
        Long userIDAsLong = Long.parseLong(userID);
        Long sessionIDAsLong = Long.parseLong(sessionID);

        User userToLeaveSession = userRepository.findAllById(userIDAsLong);
        Session sessionUserWantsToLeave = sessionRepository.findAllById(sessionIDAsLong);

        int tryToLeaveResultCode = sessionUserWantsToLeave.removeUserFromSession(userToLeaveSession);
        sessionRepository.save(sessionUserWantsToLeave);
        return (long) tryToLeaveResultCode;
    }
}
