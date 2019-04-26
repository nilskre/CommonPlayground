package commonplayground.model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SessionRemoveUserTest {

    @Test
    public void testSuccessfulRemoovedUser() {
        Session testSession = new Session("Title", "Description", "Game", "Place", "12.12.2020", "12:00", 2, (long)100, "Genre", "Online");
        User testUser = new User("Username", "_pswAPP89.", "test@test.de");
        testSession.addUserToSession(testUser);
        assertEquals(testSession.removeUserFromSession(testUser), 0);
        assertFalse(testSession.getUsers().contains(testUser));
    }

    @Test
    public void testUserIsHostRemovingFails() {
        User testUser = new User("Username", "_pswAPP89.", "test@test.de");
        Long hostId = testUser.getId();
        Session testSession = new Session("Title", "Description", "Game", "Place", "12.12.2020", "12:00", 2, hostId, "Genre", "Online");
        assertEquals(testSession.removeUserFromSession(testUser), -20);
    }

    @Test
    public void testUserIsHost() {
        User testUser = new User("Username", "_pswAPP89.", "test@test.de");
        Long hostId = testUser.getId();
        Session testSession = new Session("Title", "Description", "Game", "Place", "12.12.2020", "12:00", 2, hostId, "Genre", "Online");
        assertTrue(testSession.userIsHost(testUser));
    }
}