package commonplayground.controller.cucumber.api;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"src/test/resources/cucumber/api", "cucumber.api.spring"}
)//features = "src/test/resources/cucumber/api", cucumber.api.spring)
public class Test {
}