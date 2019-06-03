package commonplayground.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TestData {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private String todaysDate = simpleDateFormat.format(new Date());

    private ArrayList<Session> testSessions = new ArrayList<>();
    private User testUser = new User("iBims", "123456789", "test@test.de");
    private Message testMessage = new Message("TESTMASSAGE: Join request for AB","CD wants to join this session", (long)1,(long)2, "CommonPlayground");
    private static int testUserID;

    private void fillSessionData(){
        Session testSession1 = new Session("Card Game", "Card fun", "Doppelkopf", "76646", "22-11-2024", "12:00", 4, Long.parseLong(String.valueOf(testUserID)), "genre", "offline");
        Session testSession2 = new Session("Card Game", "Card fun", "Doppelkopf", "76646", "22-11-2024", "12:00", 4, Long.parseLong(String.valueOf(testUserID)), "abcd", "offline");
        Session testSession3 = new Session("GW2 World Boss Run", "Tequatl->Behemoth->Destroyer", "Guild Wars 2", "76131", "12-12-2020", "15:00", 50, Long.parseLong(String.valueOf(testUserID)), "genre", "online");
        testSessions.add(testSession1);
        testSessions.add(testSession2);
        testSessions.add(testSession3);
        testUser.addMessage(testMessage);
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
