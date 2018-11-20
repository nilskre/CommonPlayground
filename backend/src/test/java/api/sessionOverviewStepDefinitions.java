package api;

import cucumber.api.CucumberOptions;
import cucumber.api.PendingException;
import cucumber.api.java.en.*;



public class sessionOverviewStepDefinitions {

    @Given("^I send a request to the backend$")
    public void theUserIsLoggedIn() {

    }

    @When("^I look for \"([^\"]*)\"$")
    public void iLookFor(String requestHeader){

    }

    @Then("^There should be sessions$")
    public void thereShouldBeSessions(){

    }
}
