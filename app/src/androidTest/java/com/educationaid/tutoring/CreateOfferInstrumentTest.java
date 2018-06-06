package com.educationaid.tutoring;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.educationaid.tutoring.Model.User;
import com.educationaid.tutoring.WebService.WebService;

import org.json.JSONArray;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Created by fabianhofmann on 12.04.18.
 */

public class CreateOfferInstrumentTest {
    static WebService webService = null;
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
        onView(withId(R.id.txt_title_create_offer)).perform(typeText("Analysis 1"));
        closeSoftKeyboard();
        onView(withId(R.id.txt_description_create_offer)).perform(typeText("Nachhilfestunde Montags 9:00 im LZ 2"));
        closeSoftKeyboard();
        onView(withId(R.id.btnMenuAdd)).perform(click());
    }

    @Test
    public void addAOfferWithoutDescription() throws  Exception {
        onView(withId(R.id.txt_title_create_offer)).perform(typeText("Analysis 1"));
        closeSoftKeyboard();
        onView(withId(R.id.btn_creat_offer)).perform(click());
    }
    @Test
    public void addAOfferWithoutTitle() throws  Exception {
        onView(withId(R.id.txt_description_create_offer)).perform(typeText("Nachhilfestunde Montags 9:00 im LZ 2"));
        closeSoftKeyboard();
        onView(withId(R.id.btnMenuAdd)).perform(click());
    }


}

