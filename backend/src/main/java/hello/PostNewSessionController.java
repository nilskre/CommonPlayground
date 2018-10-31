package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class PostNewSessionController {
    private SessionList sessionList = new SessionList();

    @RequestMapping("/postNewSession")
    public Session postNewSession(@RequestParam(value="name", defaultValue="not given") String name,
                                  @RequestParam(value="game", defaultValue = "not given") String game,
                                  @RequestParam(value="place", defaultValue = "not given") String place,
                                  @RequestParam(value="date", defaultValue = "not given") String date,
                                  @RequestParam(value="numberOfPlayers", defaultValue = "1") int numberOfPlayers) {
        Session addedSession = new Session(name, game, place, date, numberOfPlayers);
        this.sessionList.addSession(addedSession);
        return addedSession;
    }
}