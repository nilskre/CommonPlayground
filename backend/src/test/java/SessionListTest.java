import hello.Session;
import hello.SessionList;
import org.junit.BeforeClass;
import org.junit.Test;

public class SessionListTest {
    private static SessionList testSessionList;

    @BeforeClass
    public static void setUp() {
        testSessionList = new SessionList();
    }

    @Test
    public void testAddSessionToList() {
        Session testSession = new Session("Name", "Game", "Place", "Date", 42);
        testSessionList.addSession(testSession);
        assert (testSessionList.getAllSessions().contains(testSession));
    }

    @Test
    public void testRemoveSessionFromList() {
        Session testSession = new Session("Name", "Game", "Place", "Date", 42);
        testSessionList.addSession(testSession);
        testSessionList.removeSession(testSession);
        assert (!testSessionList.getAllSessions().contains(testSession));
    }
}