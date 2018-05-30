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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Created by fabianhofmann on 11.04.18.
 */

public class TutorHomeScreenInstrumentTest {

    static WebService webService = null;

    @BeforeClass
    public static void Init() throws Exception
    {
        webService = new WebService();
        JSONArray obj = new JSONArray( webService.Login("john@example.com", "1234"));

        HomeActivity.currentUser = new User(
                Integer.valueOf(obj.getJSONObject(0).getString("u_id")),
                obj.getJSONObject(0).getString("first_name"),
                obj.getJSONObject(0).getString("last_name"),
                false,
                obj.getJSONObject(0).getString("email"),
                Integer.valueOf(obj.getJSONObject(0).getString("admin")),
                null);
    }

    @Rule
    public ActivityTestRule<TutorHomeScreenActivity> mActivityRule =
            new ActivityTestRule<>(TutorHomeScreenActivity.class);


    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.educationaid.tutoring", appContext.getPackageName());
    }
    @Test
    public void useAddButton() throws  Exception {
        onView(withId(R.id.buttonAdd)).perform(click());
    }
    @Test
    public void isOffersArePresent() throws  Exception {
        onView(withText("Your offers")).check(matches(isDisplayed()));
    }
}
