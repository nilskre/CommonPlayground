package commonplayground.controller;

import commonplayground.model.Session;
import commonplayground.model.SessionRepository;
import commonplayground.model.User;
import commonplayground.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostNewSessionController {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostNewSessionController(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/postNewSession")
    public void postNewSession(@RequestParam(value = "title", defaultValue = "not given") String title,
                               @RequestParam(value = "description", defaultValue = "not given") String description,
                               @RequestParam(value = "game", defaultValue = "not given") String game,
                               @RequestParam(value = "place", defaultValue = "not given") String place,
                               @RequestParam(value = "date", defaultValue = "not given") String date,
                               @RequestParam(value = "time", defaultValue = "not given") String time,
                               @RequestParam(value = "numberOfPlayers", defaultValue = "1") int numberOfPlayers,
                               @RequestParam(value = "idOfHost", defaultValue = "-1") String idOfHost,
                               @RequestParam(value = "genre", defaultValue = "not given")String genre,
                               @RequestParam(value= "isOnline", defaultValue = "not given")Boolean isOnline){
        Long idOfHostAsLong = Long.parseLong(idOfHost);
        User sessionHost = userRepository.findAllById(idOfHostAsLong);
        Session addedSession = new Session(title, description, game, place, date, time, numberOfPlayers, idOfHostAsLong, genre, isOnline);
        addedSession.addUserToSession(sessionHost);
        sessionRepository.save(addedSession);
    }
}