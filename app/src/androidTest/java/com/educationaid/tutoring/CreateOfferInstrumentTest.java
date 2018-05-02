package com.educationaid.tutoring;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
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
    
    @Test
    public void clickAddButton() throws  Exception {
        onView(withId(R.id.btnMenuAdd)).perform(click());
    }
    @Test
    public void addAOffer() throws  Exception {
        onView(withId(R.id.title_label)).perform(typeText("Analysis 1"));
        onView(withId(R.id.descripiton_label)).perform(typeText("Nachhilfestunde Montags 9:00 im LZ 2"));
        onView(withId(R.id.btnMenuAdd)).perform(click());
    }

    @Test
    public void addAOfferWithoutDescription() throws  Exception {
        onView(withId(R.id.title_label)).perform(typeText("Analysis 1"));
        onView(withId(R.id.btnMenuAdd)).perform(click());
    }
    @Test
    public void addAOfferWithoutTitle() throws  Exception {
        onView(withId(R.id.descripiton_label)).perform(typeText("Nachhilfestunde Montags 9:00 im LZ 2"));
        onView(withId(R.id.btnMenuAdd)).perform(click());
    }


}

