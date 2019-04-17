package commonplayground.controller;

import commonplayground.model.Session;
import commonplayground.model.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MyHostedSessions {

    private final SessionRepository sessionRepository;

    @Autowired
    public MyHostedSessions(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @RequestMapping("/getMyHostedSessions")
    public ArrayList<Session> getMyHostedSessions(@RequestParam(value = "userID", defaultValue = "not given") String userID) {
        Long userIDAsLong = Long.parseLong(userID);

        return sessionRepository.findAllByIdOfHost(userIDAsLong);
    }
}
