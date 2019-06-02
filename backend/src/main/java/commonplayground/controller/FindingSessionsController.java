package commonplayground.controller;


import commonplayground.model.Session;
import commonplayground.model.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class FindingSessionsController {

    private final SessionRepository sessionRepository;

    private ArrayList<Session> matchingSessions = new ArrayList<>();
    private String genre;
    private String place;

    @Autowired
    public FindingSessionsController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;

    }

    @RequestMapping("/findSessions")
    public ArrayList<Session> postNewSession(@RequestParam(value = "place", defaultValue = "not given") String place,
                                             @RequestParam(value = "genre", defaultValue = "not given") String genre,
                                             @RequestParam(value = "isOnline", defaultValue = "not given") String isOnline) {
        this.genre= genre;
        this.place= place;

        if (isOnline.equals("online")){
            matchingSessions= matchOnlineSessions();
        }else if (isOnline.equals("offline")){
            matchingSessions= matchOfflineSessions();
        }
        return matchingSessions;
    }

    private ArrayList<Session> matchOnlineSessions() {
        ArrayList<Session> onlineSessions = new ArrayList<>();
        onlineSessions= sessionRepository.findAllByIsOnline("true");
        //onlineSessions= matchSessionsByPlace(onlineSessions);
        onlineSessions= matchSessionsByGenre(onlineSessions);

        return onlineSessions;
    }

    private ArrayList<Session> matchSessionsByPlace(ArrayList<Session> sessionsToMatch) {
        for (Session session: sessionsToMatch) {
            //TODO send API Request
        }
        return sessionsToMatch;
    }

    private ArrayList<Session> matchOfflineSessions() {
        ArrayList<Session> offlineSessions = new ArrayList<>();
        offlineSessions= sessionRepository.findAllByIsOnline("offline");
        offlineSessions= matchSessionsByGenre(offlineSessions);
        return offlineSessions;
    }

    private ArrayList<Session> matchSessionsByGenre(ArrayList<Session> sessionsToMatch) {
        ArrayList<Session> matchingSessions = new ArrayList<>();
        for (Session session: sessionsToMatch) {
            if (session.getGenre().equals(genre)){
                matchingSessions.add(session);
            }
        }
        return matchingSessions;
    }
}