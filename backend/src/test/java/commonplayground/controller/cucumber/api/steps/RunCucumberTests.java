package commonplayground.controller.cucumber.api.steps;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"cucumber.api.spring", "commonplayground.controller.cucumber.api.steps"},
        features = {"src/test/resources/cucumber/api"}
)
//@Ignore
public class RunCucumberTests {
}