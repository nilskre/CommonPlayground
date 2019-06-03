package commonplayground.controller;


import commonplayground.model.Session;
import commonplayground.model.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
        onlineSessions= matchSessionsByGenre(onlineSessions);

        return onlineSessions;
    }

    private ArrayList<Session> matchOfflineSessions() {
        ArrayList<Session> offlineSessions = new ArrayList<>();
        offlineSessions= sessionRepository.findAllByIsOnline("offline");
        offlineSessions= matchSessionsByPlace(offlineSessions);
        offlineSessions= matchSessionsByGenre(offlineSessions);
        return offlineSessions;
    }

    private ArrayList<Session> matchSessionsByPlace(ArrayList<Session> sessionsToMatch) {
        ArrayList<Session> sessionsByPlace = new ArrayList<>();
        String cityToMatch= sendRequestToPlaceAPI(place);
        //String cityToMatch= "123";
        //String cityOfSession= "123";

        for (Session session: sessionsToMatch) {
            String cityOfSession= sendRequestToPlaceAPI(session.getPlace());
            if (cityOfSession.equals(cityToMatch)){
                sessionsByPlace.add(session);
            }
        }
        return sessionsByPlace;
    }

    private String sendRequestToPlaceAPI(String place){
        String city = "123";
        try {
            URL url = new URL("http://api.zippopotam.us/de/" + place);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
            { content.append(inputLine);
            }
            in.close();
            System.out.println("API ANTWORT !!!!!!!!!!!!!!!!!!!!!!!: " + content.toString());
            System.out.println("Char AT: " + content.substring(102));
            System.out.println("CITY: " + getCityOutOfResponse(content.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }

    private String getCityOutOfResponse(String response){
        String city = new String();
        int iterationValue= 102;
        while (response.charAt(iterationValue) != '"') {
            city+= response.charAt(iterationValue);
            iterationValue++;
        }
        return city;
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