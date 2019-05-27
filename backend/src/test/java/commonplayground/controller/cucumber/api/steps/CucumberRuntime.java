package commonplayground.controller.cucumber.api.steps;

import commonplayground.controller.cucumber.api.globaldict.GlobalSessionId;
import commonplayground.model.MessageRepository;
import commonplayground.model.SessionRepository;
import commonplayground.model.UserRepository;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration
@Ignore
public class CucumberRuntime {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public CucumberRuntime(SessionRepository sessionRepository, UserRepository userRepository, MessageRepository messageRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Before
    public void testSetUpCucumber() {
        System.out.println("\nEXECUTE CUCUMBER CONTEXT CLASS\n");
    }

    @After
    public void testTearDownCucumber() {
        System.out.println("\nEXIT CUCUMBER CONTEXT CLASS\n");

        // delete data from database and unset global variables
        sessionRepository.deleteAll();
        userRepository.deleteAll();
        messageRepository.deleteAll();
        GlobalSessionId.setSessionID(null);
    }
}