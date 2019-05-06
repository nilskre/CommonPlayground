package commonplayground.controller;

import commonplayground.model.Session;
import commonplayground.model.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class SessionOverviewController {

    private List<Session> sessions = new ArrayList<>();
    private final SessionRepository sessionRepository;

    @Autowired
    public SessionOverviewController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @RequestMapping("/getSessionList")
    public List<Session> getSessionList() throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date currentDate = new Date();

        sessions.clear();
        for (Session session : sessionRepository.findAll()) {
            Date sessionDate = simpleDateFormat.parse(session.getDate());

            if (currentDate.compareTo(sessionDate) < 0) {
                sessions.add(session);
            }
        }
        return sessions;
    }
}