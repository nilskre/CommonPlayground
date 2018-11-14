package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class SessionOverviewController {

    private SessionRepository sessionRepository;
    private List sessions = new ArrayList();

    @RequestMapping("/sessionList")
    public String getSessionList() {
        String hi= "hi";
        return hi;
    }
}