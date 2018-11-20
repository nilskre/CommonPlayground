package api;

import cucumber.api.CucumberOptions;
import cucumber.api.PendingException;
import cucumber.api.java.en.*;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class sessionOverviewStepDefinitions {

    @Given("^I send a request to the backend$")
    public void iSendARequestToTheBackend() {
        get().;
    }

    @When("^I look for \"([^\"]*)\"$")
    public void iLookFor(String requestHeader){

    }

    @Then("^There should be sessions$")
    public void thereShouldBeSessions(){

    }
}
