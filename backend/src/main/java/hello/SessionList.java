package hello;

import java.util.ArrayList;
import java.util.List;

public class SessionList {
    private List<Session> allSessions = new ArrayList<>();

    void addSession(Session session){
        this.allSessions.add(session);

        System.out.println(allSessions);
    }
}