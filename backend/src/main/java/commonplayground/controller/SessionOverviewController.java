package commonplayground.controller;

import commonplayground.model.Session;
import commonplayground.model.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
        Date today = new Date();
        Date yesterday = getYesterday();
        simpleDateFormat.format(today);

        for (Session session : sessionRepository.findAll()) {
            Date sessionDate = simpleDateFormat.parse(session.getDate());
            String sessionTime = session.getTime();

            if (today.compareTo(sessionDate) < 0 ) {
                sessions.add(session);
            }
            else if (today.compareTo(sessionDate) > 0 && yesterday.compareTo(sessionDate) < 0  && isSessionTimeBeforeNow(sessionTime) ){
                sessions.add(session);
            }
        }
        return sessions;
    }

    private Date getYesterday(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    private boolean isSessionTimeBeforeNow(String sessionTime ){
        Calendar calendar = Calendar.getInstance();
        int sessionHour= getHourOfSessionAsInt(sessionTime);
        int sessionMinute= getMinutesOfSessionAsInt(sessionTime);

        boolean isAfterNow= false;
         if (sessionHour > calendar.get(Calendar.HOUR_OF_DAY)){
             isAfterNow= true;
             System.out.println("HIER" + "SessionHour: " + sessionHour);
             System.out.println("CALENDAR HOUR: " + calendar.get(Calendar.HOUR_OF_DAY));
         }
         else if (sessionHour == calendar.get(Calendar.HOUR_OF_DAY) && sessionMinute > calendar.get(Calendar.MINUTE)){
             isAfterNow= true;
         }

        return isAfterNow;
    }

    private int getHourOfSessionAsInt(String sessionTime){
        String firstTwoChars= sessionTime.substring(0,2);
        return Integer.valueOf(firstTwoChars);
    }

    private int getMinutesOfSessionAsInt(String sessionTime){
        String lastTwoChars= sessionTime.substring(3,5);
        return Integer.valueOf(lastTwoChars);
    }
}