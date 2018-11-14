package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class SessionOverviewController {

    private SessionRepository sessionRepository;
    private List sessions = new ArrayList();
    private Session session1;
    private Session session2;

    @RequestMapping("/getSessionList")
    public List<Session> getSessionList() {

        //session1= new Session("lol", "dkwoap", "jdwakl", "buidjwnk", "ndiwu", 23);
        //session2= new Session("aaa", "bbbb", "cccc", "dddd", "eeee", 12);
        //sessions.add(session1);
        //sessions.add(session2);
        for (Session s: sessionRepository.findAll()
             ) {
            System.out.println(s.toString());
            sessions.add(s);
        }

        return sessions;
    }
}