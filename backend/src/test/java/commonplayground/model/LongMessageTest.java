package commonplayground.model;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongMessageTest {
    private static Message testMessage;

    @BeforeClass
    public static void setUp() {
        testMessage = new Message("MessageTitle", "MessageDescription", (long) 1, (long) 2, "CommonPlayground");
    }

    @Test
    public void testType() {
        assertEquals(testMessage.getType(), "JoinRequest");
    }

    @Test
    public void testTitle() {
        assertEquals(testMessage.getTitle(), "MessageTitle");
    }

    @Test
    public void testDescription() {
        assertEquals(testMessage.getDescription(), "MessageDescription");
    }

    @Test
    public void testUserIdWhoWantsToJoin() {
        assertEquals(testMessage.getUserIdWhoWantsToJoin(), (long) 1);
    }

    @Test
    public void testSessionIdUserWantsToJoin() {
        assertEquals(testMessage.getSessionIdUserWantsToJoin(), (long) 2);
    }

    @Test
    public void testAuthorName() {
        assertEquals(testMessage.getAuthorName(), "CommonPlayground");
    }
}