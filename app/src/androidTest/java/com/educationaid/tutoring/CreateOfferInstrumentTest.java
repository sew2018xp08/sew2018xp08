package com.educationaid.tutoring;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Created by fabianhofmann on 12.04.18.
 */

public class CreateOfferInstrumentTest {
    @Rule
    public ActivityTestRule<CreateOfferActivity> mActivityRule =
            new ActivityTestRule<>(CreateOfferActivity.class);
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.educationaid.tutoring", appContext.getPackageName());
    }
    /*
    @Test
    public void useLogoutButton() throws  Exception {
        onView(withText("Add")).perform(click());
    }
    */

    @Test
    public void isTitleLabelPresent() throws  Exception {
        onView(withText("Title")).check(matches(isDisplayed()));
    }
    /*
    @Test
    public void useAddButton() throws  Exception {
        onView(withText("+")).perform(click());
    }
    @Test
    public void isOffersArePresent() throws  Exception {
        onView(withText("Your offers")).check(matches(isDisplayed()));
    }
    @Test
    public void isOffersArePresent2() throws  Exception {
        onView(withText("English")).check(matches(isDisplayed()));
    }
    */
}
