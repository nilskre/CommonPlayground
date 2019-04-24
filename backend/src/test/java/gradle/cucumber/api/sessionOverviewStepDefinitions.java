package gradle.cucumber.api;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static io.restassured.RestAssured.get;


public class sessionOverviewStepDefinitions {

    @And("^I send a request to the backend$")
    public void iSendARequestToTheBackend() {
    }

    @When("^I look for \"([^\"]*)\"$")
    public void iLookFor(String requestHeader) {
        get(requestHeader).then().statusCode(200);
    }

    @Then("^There should be sessions$")
    public void thereShouldBeSessions() {
        assert get("/getSessionList").jsonPath().getList("title").contains("Card Game");
    }
}
