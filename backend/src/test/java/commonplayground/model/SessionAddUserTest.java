package commonplayground.model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SessionAddUserTest {

    @Test
    public void testSuccessfulAddedUser() {
        Session testSession = new Session("Title", "Description", "Game", "Place", "12.12.2020", "12:00", 2, (long)100, "Genre", "Online");
        User testUser = new User("Username", "_pswAPP89.", "test@test.de");
        assertEquals(testSession.addUserToSession(testUser), 0);
        assertTrue(testSession.getUsers().contains(testUser));
    }

    @Test
    public void testUserAlreadyJoinedSession() {
        Session testSession = new Session("Title", "Description", "Game", "Place", "12.12.2020", "12:00", 2, (long)100, "Genre", "Online");
        User testUser = new User("Username", "_pswAPP89.", "test@test.de");
        testSession.addUserToSession(testUser);
        assertEquals(testSession.addUserToSession(testUser), -11);
    }

    @Test
    public void testSessionIsFull() {
        Session testSession = new Session("Title", "Description", "Game", "Place", "12.12.2020", "12:00", 2, (long)100, "Genre", "Online");
        User testUser1 = new User("Username1", "_pswAPP89.", "test@test.de");
        User testUser2 = new User("Username2", "_pswAPP89.", "test@test.de");
        User testUser3 = new User("Username3", "_pswAPP89.", "test@test.de");
        testSession.addUserToSession(testUser1);
        testSession.addUserToSession(testUser2);
        assertEquals(testSession.addUserToSession(testUser3), -10);
    }
}