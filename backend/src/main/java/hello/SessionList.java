package hello;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class SessionList {
    private List<Session> allSessions = new ArrayList<>();
    
    public void addSession(Session session){
        this.allSessions.add(session);

        System.out.println(allSessions);
    }

    public void removeSession(Session session){
        this.allSessions.remove(session);
    }

    public List<Session> getAllSessions() {
        return allSessions;
    }
}