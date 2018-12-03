package commonplayground.controller;

import commonplayground.model.Session;
import commonplayground.model.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class SessionOverviewController {

    private List<Session> sessions = new ArrayList();
    private final SessionRepository sessionRepository;
    @Autowired
    public SessionOverviewController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @RequestMapping("/getSessionList")
    public List<Session> getSessionList() {
        sessions.clear();
        for (Session session: sessionRepository.findAll()) {
            sessions.add(session);
        }
        return sessions;
    }
}