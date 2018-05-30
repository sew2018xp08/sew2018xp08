package com.educationaid.tutoring;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.educationaid.tutoring.Constants.Constants;
import com.educationaid.tutoring.Model.User;
import com.educationaid.tutoring.WebService.WebService;

import junit.framework.Assert;

import org.json.JSONArray;
import org.junit.After;
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

public class HomeScreenInstrumentedTest {


    private static WebService webService;
    private static User testUser;

    @BeforeClass
    public static void Init() throws Exception {
        webService = new WebService();
        webService.Register("Max", "Mustermann", "test@test.com", "1234", "1");
        JSONArray obj = new JSONArray(webService.Login("test@test.com", "1234"));

        testUser = new User(
                Integer.valueOf(obj.getJSONObject(0).getString("u_id")),
                obj.getJSONObject(0).getString("first_name"),
                obj.getJSONObject(0).getString("last_name"),
                true,
                obj.getJSONObject(0).getString("email"),
                Integer.valueOf(obj.getJSONObject(0).getString("admin")),
                null);
    }

    @Rule
    public ActivityTestRule<HomeActivity> mActivityRule =
            new ActivityTestRule<>(HomeActivity.class);


    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.educationaid.tutoring", appContext.getPackageName());
    }

    @Test
    public void checkRecyclerView() throws Exception {
        onView(withId(R.id.offerView)).perform(click());

    }

    @Test
    public void isOffersTextPresent() throws Exception {
        onView(withText("Offers")).check(matches(isDisplayed()));
    }

    @After
    public void CleanUp() {
        Assert.assertTrue(webService.DeleteTestUserFromDataBase().equals(Constants.ANS_DELETE_USER));
    }
}
