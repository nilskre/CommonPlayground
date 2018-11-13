package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class SessionOverviewController {
    private SessionList sessionList = new SessionList();
    private List<Session> sessions = new ArrayList<>();

    @RequestMapping("/getSessionList")
    public List<Session> getSessionList() {
        sessions= sessionList.getAllSessions();
        return sessions;
    }
}