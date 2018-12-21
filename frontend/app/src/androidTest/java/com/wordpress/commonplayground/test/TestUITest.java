package com.wordpress.commonplayground.test;

import android.support.test.runner.AndroidJUnit4;

import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class TestUITest {

    /*@Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private Activity activity;

    @Before
    public void setup() {
        activityTestRule.launchActivity(new Intent());
        activity = activityTestRule.getActivity();
    }

    @After
    public void tearDown() {
        activityTestRule.finishActivity();
    }

    @Test
    public void useAppContext() {
        ViewInteraction floatingActionButton = onView(withId(R.id.fab));
        floatingActionButton.perform(click());
    }*/
}
