package commonplayground.controller.cucumber.api;

import commonplayground.Application;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.springframework.boot.SpringApplication;

import static io.restassured.RestAssured.get;

public class GlobalStepDefinitions {

    @Given("The backend is active")
    public void theBackendIsActive() {
        try {
            SpringApplication.run(Application.class);
        } catch (Exception e) {
            assert false;
        }
    }

    @When("^I look for the session list \"([^\"]*)\"$")
    public void iLookFor(String requestHeader) {
        get(requestHeader).then().statusCode(200);
    }
}
