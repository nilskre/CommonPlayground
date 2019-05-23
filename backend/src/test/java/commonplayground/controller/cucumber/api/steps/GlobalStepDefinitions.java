package commonplayground.controller.cucumber.api.steps;

import cucumber.api.java.en.When;

import static io.restassured.RestAssured.get;

public class GlobalStepDefinitions {

    @When("^I look for the session list \"([^\"]*)\"$")
    public void iLookFor(String requestHeader) {
        get(requestHeader).then().statusCode(200);
    }
}
