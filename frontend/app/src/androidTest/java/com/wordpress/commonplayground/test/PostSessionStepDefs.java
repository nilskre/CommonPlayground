package com.wordpress.commonplayground.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.wordpress.commonplayground.view.LoginActivity;
import com.wordpress.commonplayground.view.MainActivity;
import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.viewmodel.SessionManager;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.runner.RunWith;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class PostSessionStepDefs {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule;

    private Activity activity;

    @Before("@postsession-feature")
    public void setup() {
        activityTestRule = new ActivityTestRule<>(MainActivity.class, true);

        Context targetContext = getInstrumentation().getTargetContext();
        SessionManager sessionManager = new SessionManager(targetContext);
        sessionManager.logoutUser();
        sessionManager.createLoginSession("3", "test@test.de");

        Intent openAddSessionActivity = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
        activityTestRule.launchActivity(openAddSessionActivity);

        activity = activityTestRule.getActivity();

        ViewInteraction floatingActionButton = onView(withId(R.id.fab));
        floatingActionButton.perform(click());
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
        ViewInteraction textInputEditText = onView(withId(R.id.TitleInputField));
        textInputEditText.perform(typeText(testTitle), closeSoftKeyboard());
    }

    @And("^The user types the description ([^\"]*) and the input is correct$")
    public void theUserTypesTheDescriptionAndTheInputIsCorrect(String testDescription) {
        ViewInteraction textInputEditText = onView(withId(R.id.DescriptionInputField));
        textInputEditText.perform(typeText(testDescription), closeSoftKeyboard());
    }

    @And("^The user types the game ([^\"]*) and the input is correct$")
    public void theUserTypesTheGameAndTheInputIsCorrect(String testGame) {
        ViewInteraction textInputEditText = onView(withId(R.id.GameInputField));
        textInputEditText.perform(typeText(testGame), closeSoftKeyboard());
    }

    @And("^The user types the place ([^\"]*) and the input is correct$")
    public void theUserTypesThePlaceAndTheInputIsCorrect(String testPlace) {
        ViewInteraction textInputEditText = onView(withId(R.id.PlaceInputField));
        textInputEditText.perform(typeText(testPlace), closeSoftKeyboard());
    }

    @And("^The user types the date ([^\"]*) and the input is correct$")
    public void theUserTypesTheDateAndTheInputIsCorrect(String testDate) {
        ViewInteraction textInputEditText = onView(withId(R.id.DateInputField));
        textInputEditText.perform(typeText(testDate), closeSoftKeyboard());
    }

    @And("^The user types the time ([^\"]*) and the input is correct$")
    public void theUserTypesTheTimeAndTheInputIsCorrect(String testTime) {
        ViewInteraction textInputEditText = onView(withId(R.id.TimeInputField));
        textInputEditText.perform(typeText(testTime), closeSoftKeyboard());
    }

    @And("^The user types the number of players ([^\"]*) and the input is correct$")
    public void theUserTypesTheNumberOfPlayersAndTheInputIsCorrect(String testPlayersNumber) {
        ViewInteraction textInputEditText = onView(withId(R.id.PlayersInputField));
        textInputEditText.perform(typeText(testPlayersNumber), closeSoftKeyboard());
    }

    @And("^The user presses the publish button$")
    public void theUserPressesThePublishButton() {
        ViewInteraction floatingActionButton = onView(withId(R.id.ButtonPublish));
        floatingActionButton.perform(click());
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
        onView(isRoot()).perform(ViewActions.pressBack());
    }

    @Then("^No Request is sent$")
    public void noRequestIsSent() {
        //TODO test
    }
}
