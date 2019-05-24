package com.wordpress.commonplayground.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.view.MainActivity;
import com.wordpress.commonplayground.viewmodel.SessionManager;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(Cucumber.class)
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
        sessionManager.createLoginSession("4", "test@test.de");

        Intent openAddSessionActivity = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), MainActivity.class);
        activityTestRule.launchActivity(openAddSessionActivity);

        activity = activityTestRule.getActivity();

        ViewInteraction floatingActionButton = onView(withId(R.id.fab));
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
        ViewInteraction textInputEditText = onView(withId(R.id.TitleInputField));
        textInputEditText.perform(ViewActions.typeText(testTitle), ViewActions.closeSoftKeyboard());
    }

    @And("^The user types the game ([^\"]*) and the input is correct$")
    public void theUserTypesTheGameAndTheInputIsCorrect(String testGame) {
        ViewInteraction textInputEditText = onView(withId(R.id.GameInputField));
        textInputEditText.perform(ViewActions.typeText(testGame), ViewActions.closeSoftKeyboard());
    }

    @And("^The user chooses ([^\"]*) as type$")
    public void theUserChoosesTypeAsType(String testType) {
        ViewInteraction spinner = onView(withId(R.id.type_spinner));
        spinner.perform(scrollTo(), click());
        ViewInteraction textView = onView(withText(testType));
        textView.perform(click());
    }

    @And("^The user chooses ([^\"]*) as genre$")
    public void theUserChoosesGenreAsGenre(String testGenre) {
        ViewInteraction spinner = onView(withId(R.id.genre_spinner));
        spinner.perform(scrollTo(), click());
        ViewInteraction textView = onView(withText(testGenre));
        textView.perform(click());
    }

    @And("^The user types the post code ([^\"]*) and the input is correct$")
    public void theUserTypesThePlaceAndTheInputIsCorrect(String testPlace) {
        if (!"-".equals(testPlace)) {
            ViewInteraction textInputEditText = onView(withId(R.id.PlaceInputField));
            textInputEditText.perform(ViewActions.typeText(testPlace), ViewActions.closeSoftKeyboard());
        }
    }

    @And("^The user picks the date ([^\"]*)$")
    public void theUserTypesTheDateAndTheInputIsCorrect(String testDate) {
        ViewInteraction buttonDate = onView(withId(R.id.btn_date));
        buttonDate.perform(click());
        String [] date = testDate.split("-");
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0])));
        ViewInteraction buttonAccept = onView(withText("OK"));
        buttonAccept.perform(click());
    }

    @And("^The user picks the time ([^\"]*)$")
    public void theUserTypesTheTimeAndTheInputIsCorrect(String testTime) {
        ViewInteraction buttonTime = onView(withId(R.id.btn_time));
        buttonTime.perform(scrollTo(), click());
        String [] time = testTime.split(":");
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(Integer.parseInt(time[0]), Integer.parseInt(time[1])));
        ViewInteraction buttonAccept = onView(withText("OK"));
        buttonAccept.perform(click());
    }

    @And("^The user types the number of players ([^\"]*) and the input is correct$")
    public void theUserTypesTheNumberOfPlayersAndTheInputIsCorrect(String testPlayersNumber) {
        ViewInteraction textInputEditText = onView(withId(R.id.PlayersInputField));
        textInputEditText.perform(scrollTo(), ViewActions.typeText(testPlayersNumber), ViewActions.closeSoftKeyboard());
    }

    @And("^The user types the description ([^\"]*) and the input is correct$")
    public void theUserTypesTheDescriptionAndTheInputIsCorrect(String testDescription) {
        ViewInteraction textInputEditText = onView(withId(R.id.DescriptionInputField));
        textInputEditText.perform(ViewActions.typeText(testDescription), ViewActions.closeSoftKeyboard());
    }

    @And("^The user presses the publish button$")
    public void theUserPressesThePublishButton() {
        ViewInteraction floatingActionButton = onView(withId(R.id.ButtonPublish));
        floatingActionButton.perform(click());
    }

    @Then("^The posting screen is closed$")
    public void thePostingScreenIsClosed() {
        onView(withId(R.id.rvSessions));
    }

    @And("^The new session with ([^\"]*) is shown$")
    public void theNewSessionWithTitleIsShown(String title) {
        onView(withText(title));
    }

    @When("^The user presses the Back button$")
    public void theUserPressesTheBackButton() {
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack());
    }

    @Then("^No Request is sent$")
    public void noRequestIsSent() {
        Assert.assertNotNull(activity);
    }
}