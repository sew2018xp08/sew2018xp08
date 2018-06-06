package com.educationaid.tutoring;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.educationaid.tutoring.Constants.Constants;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Created by fabianhofmann on 11.04.18.
 */

public class TutorHomeScreenInstrumentTest {
    @Rule
    public ActivityTestRule<HomeActivity> mActivityRule =
            new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.educationaid.tutoring", appContext.getPackageName());
    }
    @Test
    public void useAddButton() throws  Exception {
        if(HomeActivity.currentUser.getUserId() != Constants.NOT_LOGGED_IN) {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Logout")).perform(click());
        }

        doTestLogin();
        onView(withId(R.id.buttonAdd)).perform(click());
    }
    @Test
    public void isOffersArePresent() throws  Exception {
        doTestLogin();
        onView(withText("Your offers")).check(matches(isDisplayed()));
    }


    public void doTestLogin() {
        if (HomeActivity.currentUser.getUserId() != Constants.NOT_LOGGED_IN) {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Logout")).perform(click());
        }
        onView(withId(R.id.btnMenuLogin)).perform(click());
        onView(withId(R.id.txtUserName)).perform(typeText("testpic@gmail.com"));
        onView(withId(R.id.txtPassword)).perform(typeText("1234"));
        onView(withId(R.id.btnLogin)).perform(click());
    }
}
