package api;

import cucumber.api.java.en.*;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class sessionOverviewStepDefinitions {

    @Given("^I send a request to the backend$")
    public void iSendARequestToTheBackend() {
    }

    @When("^I look for \"([^\"]*)\"$")
    public void iLookFor(String requestHeader){
        get("/getSessionList").then().statusCode(200);
    }

    @Then("^There should be sessions$")
    public void thereShouldBeSessions(){
        get("/getSessionList").then().statusCode(200);
    }
}
