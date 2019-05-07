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
public class MyJoinedSessionsController {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    @Autowired
    public MyJoinedSessionsController(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/getMyJoinedSessions")
    public ArrayList<Session> getMySessions(@RequestParam(value = "userID", defaultValue = "not given") String userID) {
        Long userIDAsLong = Long.parseLong(userID);
        User relevantUser = userRepository.findAllById(userIDAsLong);

        ArrayList<Session> sessionsUserJoined = sessionRepository.findAllByUsers(relevantUser);

        for (int i = 0; i < sessionsUserJoined.size(); i++) {
            User sessionHost = sessionsUserJoined.get(i).getUsers().get(0);
            if (sessionHost.equals(relevantUser)) {
                sessionsUserJoined.remove(i);
                i --;
            }
        }

        return sessionsUserJoined;
    }
}
