package commonplayground.controller.session;

import commonplayground.Application;
import commonplayground.controller.PlaceAPI;
import commonplayground.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class PostNewSessionController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private String city;
    private PlaceAPI placeAPI = new PlaceAPI();

    @Autowired
    public PostNewSessionController(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/postNewSession")
    public Long postNewSession(@RequestParam(value = "title", defaultValue = "not given") String title,
                               @RequestParam(value = "description", defaultValue = "not given") String description,
                               @RequestParam(value = "game", defaultValue = "not given") String game,
                               @RequestParam(value = "place", defaultValue = "not given") String place,
                               @RequestParam(value = "date", defaultValue = "not given") String date,
                               @RequestParam(value = "time", defaultValue = "not given") String time,
                               @RequestParam(value = "numberOfPlayers", defaultValue = "1") int numberOfPlayers,
                               @RequestParam(value = "idOfHost", defaultValue = "-1") String idOfHost,
                               @RequestParam(value = "genre", defaultValue = "not given")String genre,
                               @RequestParam(value= "isOnline", defaultValue = "not given")String isOnline) throws CorruptFrontendException {
        Long idOfHostAsLong = Long.parseLong(idOfHost);
        if (userRepository.findAllById(idOfHostAsLong) != null) {
            User sessionHost = userRepository.findAllById(idOfHostAsLong);
            city= placeAPI.sendRequestToPlaceAPI(place);

            Session addedSession = new Session(title, description, game, city, date, time, numberOfPlayers, idOfHostAsLong, genre, isOnline.toLowerCase());
            addedSession.addUserToSession(sessionHost);
            sessionRepository.save(addedSession);

            return addedSession.getId();
        } else {
            log.info("Corrupt Frontend tried to access Backend");
            throw new CorruptFrontendException();
        }
    }
}