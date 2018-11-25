package hello.controller;

import hello.database.Session;
import hello.database.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostNewSessionController {

    private final SessionRepository sessionRepository;

    @Autowired
    public PostNewSessionController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @RequestMapping("/postNewSession")
    public void postNewSession(@RequestParam(value = "title", defaultValue = "not given") String title,
                               @RequestParam(value = "description", defaultValue = "not given") String description,
                               @RequestParam(value = "game", defaultValue = "not given") String game,
                               @RequestParam(value = "place", defaultValue = "not given") String place,
                               @RequestParam(value = "date", defaultValue = "not given") String date,
                               @RequestParam(value = "numberOfPlayers", defaultValue = "1") int numberOfPlayers,
                               @RequestParam(value = "idOfHost", defaultValue = "-1") Long idOfHost){
        Session addedSession = new Session(title, description, game, place, date, numberOfPlayers);
        sessionRepository.save(addedSession);
    }
}