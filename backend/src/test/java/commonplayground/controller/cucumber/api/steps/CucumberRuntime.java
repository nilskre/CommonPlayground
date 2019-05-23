package commonplayground.controller.cucumber.api.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration
@Ignore
public class CucumberRuntime {

    @Before
    public void testSetUpCucumber() {
        System.out.println("\nEXECUTE CUCUMBER CONTEXT CLASS\n");
    }

    @After
    public void testTearDownCucumber() {
        System.out.println("\nEXIT CUCUMBER CONTEXT CLASS\n");
    }
}