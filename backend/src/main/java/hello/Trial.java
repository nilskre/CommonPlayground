package hello;

import java.util.ArrayList;
import java.util.List;

public class Trial {

    public static SessionRepository sessionRepository;

    public static void main(String[] args) {

        List<Session> sessions= new ArrayList<>();
        for (Session s: sessionRepository.findAll()
                ) {
            System.out.println(s.toString());
            sessions.add(s);
        }
    }
}
