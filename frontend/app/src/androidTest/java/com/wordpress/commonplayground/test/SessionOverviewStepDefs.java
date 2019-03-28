package com.wordpress.commonplayground.test;

import android.app.Activity;
import android.content.Intent;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.view.MainActivity;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.runner.RunWith;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class SessionOverviewStepDefs {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class, true);

    private Activity activity = activityTestRule.getActivity();

    @Before("@sessionoverview-feature")
    public void setup() {
        Intent intent = new Intent();
        intent.putExtra("userID", "1");
        activityTestRule.launchActivity(intent);
        activity = activityTestRule.getActivity();
    }

    @After("@sessionoverview-feature")
    public void tearDown() {
        activityTestRule.finishActivity();
    }

    @When("^Activity Session Overview is open$")
    public void activitySessionOverviewIsOpen() {
        Assert.assertNotNull(activity);
    }

    @Then("^The page should list all the current active Sessions$")
    public void thePageShouldListAllTheCurrentActiveSessions() {
        //TODO test
    }

    @And("^For each Session should the title ([^\"]*) be shown$")
    public void forEachSessionShouldTheTitleTitleBeShown() {
        //TODO test
    }

    @And("^For each Session should the game ([^\"]*) be shown$")
    public void forEachSessionShouldTheGameGameBeShown() {
        //TODO test
    }

    @And("^For each Session should the place ([^\"]*) be shown$")
    public void forEachSessionShouldThePlacePlaceBeShown() {
        //TODO test
    }

    @And("^For each Session should the date ([^\"]*) be shown$")
    public void forEachSessionShouldTheDateDateBeShown() {
        //TODO test
    }

    @And("^For each Session should the players count ([^\"]*) be shown$")
    public void forEachSessionShouldThePlayersCountBeShown(String arg0) {
        //TODO test
    }
}
