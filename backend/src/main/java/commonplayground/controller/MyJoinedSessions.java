package commonplayground.controller;

import commonplayground.model.Session;
import commonplayground.model.SessionRepository;
import commonplayground.model.User;
import commonplayground.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MyJoinedSessions {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    @Autowired
    public MyJoinedSessions(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/getMyJoinedSessions")
    public ArrayList<Session> postNewSession(@RequestParam(value = "userID", defaultValue = "not given") String userID) {
        Long userIDAsLong = Long.parseLong(userID);
        User getSessionsUserJoined = userRepository.findAllById(userIDAsLong);

        return sessionRepository.findAllByUsers(getSessionsUserJoined);
    }
}
