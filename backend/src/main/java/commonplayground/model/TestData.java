package commonplayground.model;

import java.util.ArrayList;

public class TestData {

    ArrayList<Session> testSessions = new ArrayList<>();

    private void fillSessionData(){
        Session testSession1 = new Session("Card Game", "Card fun", "Doppelkopf", "Schlosspark", "22-11-2024", "12:00", 4, Long.parseLong("4"), "genre", "false");
        Session testSession2 = new Session("Raid", "Raid together", "CS", "WWW", "12-12-2018", "16:00", 42, Long.parseLong("4"), "genre", "true");
        Session testSession3 = new Session("GW2 World Boss Run", "Tequatl->Behemoth->Destroyer", "Guild Wars 2", "Lionsarch", "12-12-2020", "15:00", 50, Long.parseLong("4"), "genre", "true");
        testSessions.add(testSession1);
        testSessions.add(testSession2);
        testSessions.add(testSession3);
    }

    public ArrayList<Session> getTestSessions(){
        fillSessionData();
        return testSessions;
    }

}
