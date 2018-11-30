import commonplayground.model.Session;
import commonplayground.model.User;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SessionTest {
    private static Session testSession;

    @BeforeClass
    public static void setUp() {
        testSession = new Session("Title", "Description", "Game", "Place", "Date", 42, "drölf", (long)4);
    }

    @Test
    public void testName(){
        assertEquals(testSession.getTitle(), "Title");
    }

    @Test
    public void testDescription(){
        assertEquals(testSession.getDescription(), "Description");
    }

    @Test
    public void testGame(){
        assertEquals(testSession.getGame(), "Game");
    }

    @Test
    public void testPlace(){
        assertEquals(testSession.getPlace(), "Place");
    }

    @Test
    public void testDate(){
        assertEquals(testSession.getDate(), "Date");
    }

    @Test
    public void testNumberOfPlayers(){
        assertEquals(testSession.getNumberOfPlayers(), 42);
    }

    @Test
    public void testPlayers(){
        User assertHost = testSession.getUsers().get(0);
        String hostName = assertHost.getUsername();
        assertEquals(hostName, "Host");
    }
}