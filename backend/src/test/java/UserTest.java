import commonplayground.model.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
}