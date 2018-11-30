import commonplayground.model.User;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}