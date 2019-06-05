package commonplayground.controller.keeptrack;

import commonplayground.Application;
import commonplayground.model.Session;
import commonplayground.model.SessionRepository;
import commonplayground.model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MyHostedSessionsController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    @Autowired
    public MyHostedSessionsController(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/getMyHostedSessions")
    public ArrayList<Session> getMyHostedSessions(@RequestParam(value = "userID", defaultValue = "not given") String userID) throws Exception {
        Long userIDAsLong = Long.parseLong(userID);
        if (userRepository.findAllById(userIDAsLong) != null) {
            return sessionRepository.findAllByIdOfHost(userIDAsLong);
        } else {
            log.info("Corrupt Frontend tried to access Backend");
            throw new Exception("");
        }
    }
}
