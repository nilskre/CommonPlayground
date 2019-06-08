package commonplayground.controller.keeptrack;

import commonplayground.Application;
import commonplayground.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MyPendingSessionsController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    @Autowired
    public MyPendingSessionsController(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/getMyPendingSessions")
    public ArrayList<Session> getMySessions(@RequestParam(value = "userID", defaultValue = "not given") String userID) throws CorruptFrontendException {
        Long userIDAsLong = Long.parseLong(userID);
        if (userRepository.findAllById(userIDAsLong) != null) {
            User relevantUser = userRepository.findAllById(userIDAsLong);

            return sessionRepository.findAllByUserWantToJoin(relevantUser);
        } else {
            log.info("Corrupt Frontend tried to access Backend");
            throw new CorruptFrontendException();
        }
    }
}
