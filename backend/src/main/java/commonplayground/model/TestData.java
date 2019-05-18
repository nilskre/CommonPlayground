package commonplayground.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TestData {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String todaysDate = simpleDateFormat.format(new Date());

    private ArrayList<Session> testSessions = new ArrayList<>();
    private User testUser = new User("iBims", "123456789", "test@test.de");
    private Message testMessage = new Message("TESTMASSAGE: Join request for AB","CD wants to join this session", (long)1,(long)2, "CommonPlayground");

    private void fillSessionData(){

        Session testSession1 = new Session("Card Game", "Card fun", "Doppelkopf", "Schlosspark", "22-11-2024", "12:00", 4, Long.parseLong("4"), "genre", "false");
        Session testSession2 = new Session("Raid", "Raid together", "CS", "WWW", todaysDate, "16:00", 42, Long.parseLong("4"), "genre", "true");
        Session testSession3 = new Session("GW2 World Boss Run", "Tequatl->Behemoth->Destroyer", "Guild Wars 2", "Lionsarch", "12-12-2020", "15:00", 50, Long.parseLong("4"), "genre", "true");
        testSessions.add(testSession1);
        testSessions.add(testSession2);
        testSessions.add(testSession3);
    }

    public ArrayList<Session> getTestSessions(){
        fillSessionData();
        return testSessions;
    }

    public User getTestUser() {
        return testUser;
    }

    public Message getTestMessage() {
        return testMessage;
    }

}
