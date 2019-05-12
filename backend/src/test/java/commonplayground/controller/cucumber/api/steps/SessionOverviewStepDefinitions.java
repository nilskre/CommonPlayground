package commonplayground.controller.cucumber.api.steps;

import cucumber.api.java.en.Then;

import static io.restassured.RestAssured.get;


public class SessionOverviewStepDefinitions /*extends CucumberRuntime*/{

    @Then("^There should be sessions$")
    public void thereShouldBeSessions() {
        assert get("/getSessionList").jsonPath().getList("title").contains("Card Game");
    }
}
