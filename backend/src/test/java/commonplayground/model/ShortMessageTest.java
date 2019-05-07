package commonplayground.model;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShortMessageTest {
    private static Message testMessage;

    @BeforeClass
    public static void setUp() {
        testMessage = new Message("MessageTitle", "MessageDescription", "CommonPlayground");
    }

    @Test
    public void testType() {
        assertEquals(testMessage.getType(), "Info");
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
    public void testAuthorName() {
        assertEquals(testMessage.getAuthorName(), "CommonPlayground");
    }
}