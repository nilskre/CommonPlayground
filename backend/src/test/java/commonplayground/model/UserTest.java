package commonplayground.model;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    private static User testUser;

    @BeforeClass
    public static void setUp() {
        testUser = new User("Username", "_pswAPP89.", "test@test.de");
    }

    @Test
    public void testUserName() {
        assertEquals(testUser.getUsername(), "Username");
    }

    @Test
    public void testEmail() {
        assertEquals(testUser.getEmail(), "test@test.de");
    }

    @Test
    public void testPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String correctPassword = testUser.getPassword();
        assertTrue(encoder.matches("_pswAPP89.", correctPassword));
    }

    @Test
    public void testMessages() {
        Message testMessage = new Message("MessageTitle", "MessageDescription", (long) 1, (long) 2, "CommonPlayground");
        testUser.addMessage(testMessage);
        List<Message> resultList = new ArrayList<>();
        resultList.add(testMessage);

        assertEquals(testUser.getMessages(), resultList);
        testUser.removeMessage(testMessage);
    }

    @Test
    public void testAddMessage() {
        Message testMessage = new Message("MessageTitle", "MessageDescription", (long) 1, (long) 2, "CommonPlayground");
        testUser.addMessage(testMessage);
        List<Message> resultList = new ArrayList<>();
        resultList.add(testMessage);

        assertEquals(testUser.getMessages(), resultList);
        testUser.removeMessage(testMessage);
    }

    @Test
    public void testRemoveMessage() {
        Message testMessage = new Message("MessageTitle", "MessageDescription", (long) 1, (long) 2, "CommonPlayground");
        testUser.addMessage(testMessage);
        testUser.removeMessage(testMessage);
        List<Message> resultList = new ArrayList<>();

        assertEquals(testUser.getMessages(), resultList);
    }
}