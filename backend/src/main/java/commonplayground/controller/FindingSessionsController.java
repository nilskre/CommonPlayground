package commonplayground.controller;


import commonplayground.model.Session;
import commonplayground.model.SessionRepository;
import commonplayground.model.User;
import commonplayground.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.java2d.loops.GeneralRenderer;

import java.util.ArrayList;

@RestController
public class FindingSessionsController {

    private ArrayList<Session> matchingSessions = new ArrayList<>();

    @Autowired
    public FindingSessionsController(SessionRepository sessionRepository, UserRepository userRepository) {

    }

    @RequestMapping("/findSessions")
    public ArrayList<Session> postNewSession(@RequestParam(value = "place", defaultValue = "not given") String place,
                                             @RequestParam(value = "genre", defaultValue = "not given") String genre,
                                             @RequestParam(value = "isOnline", defaultValue = "not given") String isOnline) {
        if (isOnline.equals("online")){
            matchingSessions= matchOnlineSessions(place, genre);
        }else if (isOnline.equals("offline")){
            matchingSessions= matchOfflineSessions();
        }
        return matchingSessions;
    }

    private ArrayList<Session> matchOnlineSessions(String place, String genre) {
        ArrayList<Session> onlineSessions = new ArrayList<>();
        onlineSessions= Session.getAllOnlineSessions();
        onlineSessions= matchSessionsByPlace(onlineSessions);
        onlineSessions= matchSessionsByGenre(onlineSessions);

        return onlineSessions;
    }

    private ArrayList<Session> matchSessionsByPlace(ArrayList<Session> sessionsToMatch) {
        for (Session session: sessionsToMatch) {
            //TODO send API Request
        }
    }

    private ArrayList<Session> matchOfflineSessions() {
        ArrayList<Session> offlineSessions = new ArrayList<>();
        offlineSessions= Session.getAllOfflineSessions();
        offlineSessions= matchSessionsByGenre(offlineSessions);
        return offlineSessions;
    }

    private ArrayList<Session> matchSessionsByGenre(ArrayList<Session> sessionsToMatch) {
        for (Session session: sessionsToMatch) {
            //TODO Check By Genre
        }
    }
}