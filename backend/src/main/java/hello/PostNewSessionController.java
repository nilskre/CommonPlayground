package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class PostNewSessionController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private SessionList sessionList = new SessionList();

    @RequestMapping("/postNewSession")
    public Session postNewSession(@RequestParam(value="name", defaultValue="World") String name, @RequestParam(value="game", defaultValue = "OneGame") String game) {
        System.out.println("Param1: " + name);
        System.out.println("Param2: " + game);
        Session addedSession = new Session(name, game);
        this.sessionList.addSession(addedSession);
        return addedSession;
    }
}