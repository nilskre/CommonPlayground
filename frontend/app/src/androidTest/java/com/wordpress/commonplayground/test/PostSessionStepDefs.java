package com.wordpress.commonplayground.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.view.MainActivity;
import com.wordpress.commonplayground.viewmodel.SessionManager;

import org.junit.Assert;
import org.junit.Rule;

import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@CucumberOptions(
        glue = "com.wordpress.commonplayground.test",
        features = "features"
)
public class PostSessionStepDefs {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule;

    private Activity activity;

    @Before("@postsession-feature")
    public void setup() {
        activityTestRule = new ActivityTestRule<>(MainActivity.class, true);

        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SessionManager sessionManager = new SessionManager(targetContext);
        sessionManager.logoutUser();
        sessionManager.createLoginSession("3", "test@test.de");

        Intent openAddSessionActivity = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), MainActivity.class);
        activityTestRule.launchActivity(openAddSessionActivity);

        activity = activityTestRule.getActivity();

        ViewInteraction floatingActionButton = Espresso.onView(ViewMatchers.withId(R.id.fab));
        floatingActionButton.perform(ViewActions.click());
    }

    @After("@postsession-feature")
    public void tearDown() {
        activityTestRule.finishActivity();
    }

    @Given("^The user is logged in$")
    public void theUserIsLoggedIn() {
        Assert.assertNotNull(activity);
    }

    @And("^Activity New Session is open$")
    public void activityNewSessionIsOpen() {
        Assert.assertNotNull(activity);
    }

    @When("^The user types the title ([^\"]*) and the input is correct$")
    public void theUserTypesTheTitleAndTheInputIsCorrect(String testTitle) {
        ViewInteraction textInputEditText = Espresso.onView(ViewMatchers.withId(R.id.TitleInputField));
        textInputEditText.perform(ViewActions.typeText(testTitle), ViewActions.closeSoftKeyboard());
    }

    @And("^The user types the description ([^\"]*) and the input is correct$")
    public void theUserTypesTheDescriptionAndTheInputIsCorrect(String testDescription) {
        ViewInteraction textInputEditText = Espresso.onView(ViewMatchers.withId(R.id.DescriptionInputField));
        textInputEditText.perform(ViewActions.typeText(testDescription), ViewActions.closeSoftKeyboard());
    }

    @And("^The user types the game ([^\"]*) and the input is correct$")
    public void theUserTypesTheGameAndTheInputIsCorrect(String testGame) {
        ViewInteraction textInputEditText = Espresso.onView(ViewMatchers.withId(R.id.GameInputField));
        textInputEditText.perform(ViewActions.typeText(testGame), ViewActions.closeSoftKeyboard());
    }

    @And("^The user types the place ([^\"]*) and the input is correct$")
    public void theUserTypesThePlaceAndTheInputIsCorrect(String testPlace) {
       /* ViewInteraction textInputEditText = onView(withId(R.id.PlaceInputField));
        textInputEditText.perform(typeText(testPlace), closeSoftKeyboard());*/
    }

    @And("^The user types the date ([^\"]*) and the input is correct$")
    public void theUserTypesTheDateAndTheInputIsCorrect(String testDate) {
        ViewInteraction textInputEditText = Espresso.onView(ViewMatchers.withId(R.id.DateInputField));
        textInputEditText.perform(ViewActions.typeText(testDate), ViewActions.closeSoftKeyboard());
    }

    @And("^The user types the time ([^\"]*) and the input is correct$")
    public void theUserTypesTheTimeAndTheInputIsCorrect(String testTime) {
        ViewInteraction textInputEditText = Espresso.onView(ViewMatchers.withId(R.id.TimeInputField));
        textInputEditText.perform(ViewActions.typeText(testTime), ViewActions.closeSoftKeyboard());
    }

    @And("^The user types the number of players ([^\"]*) and the input is correct$")
    public void theUserTypesTheNumberOfPlayersAndTheInputIsCorrect(String testPlayersNumber) {
        ViewInteraction textInputEditText = Espresso.onView(ViewMatchers.withId(R.id.PlayersInputField));
        textInputEditText.perform(ViewActions.typeText(testPlayersNumber), ViewActions.closeSoftKeyboard());
    }

    @And("^The user presses the publish button$")
    public void theUserPressesThePublishButton() {
        ViewInteraction floatingActionButton = Espresso.onView(ViewMatchers.withId(R.id.ButtonPublish));
        floatingActionButton.perform(ViewActions.click());
    }

    @Then("^A Request is sent$")
    public void aRequestIsSent() {
        //TODO test
        Assert.assertNotNull(activity);
    }

    @And("^The posting screen is closed$")
    public void thePostingScreenIsClosed() {
        //TODO depends on other ticket
    }

    @When("^The user presses the Back button$")
    public void theUserPressesTheBackButton() {
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack());
    }

    @Then("^No Request is sent$")
    public void noRequestIsSent() {
        //TODO test
    }
}
