package com.educationaid.tutoring;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.educationaid.tutoring.Constants.Constants;
import com.educationaid.tutoring.Model.User;
import com.educationaid.tutoring.WebService.WebService;

import junit.framework.Assert;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Created by fabianhofmann on 12.04.18.
 */

public class CreateOfferInstrumentTest {
    WebService webService;
    @Before
    public void Init(){ webService = new WebService(); }

    @Rule
    public ActivityTestRule<HomeActivity> mActivityRule =
            new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        if(HomeActivity.currentUser.getUserId() != Constants.NOT_LOGGED_IN) {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Logout")).perform(click());
        }

        doTestLogin();
        onView(withId(R.id.buttonAdd)).perform(click());

        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.educationaid.tutoring", appContext.getPackageName());
    }

    @Test
    public void clickAddButton() throws  Exception {
        if(HomeActivity.currentUser.getUserId() != Constants.NOT_LOGGED_IN) {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Logout")).perform(click());
        }

        doTestLogin();
        onView(withId(R.id.buttonAdd)).perform(click());

        onView(withId(R.id.btnMenuAdd)).perform(click());
    }
    @Test
    public void addAOffer() throws  Exception {
        if(HomeActivity.currentUser.getUserId() != Constants.NOT_LOGGED_IN) {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Logout")).perform(click());
        }

        doTestLogin();
        onView(withId(R.id.buttonAdd)).perform(click());

        onView(withId(R.id.txt_title_create_offer)).perform(typeText("Analysis 1"));
        closeSoftKeyboard();
        onView(withId(R.id.txt_description_create_offer)).perform(typeText("Nachhilfestunde Montags 9:00 im LZ 2"));
        closeSoftKeyboard();
        onView(withId(R.id.btnMenuAdd)).perform(click());
    }

    @Test
    public void addAOfferTest() throws  Exception {
        if(HomeActivity.currentUser.getUserId() != Constants.NOT_LOGGED_IN) {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Logout")).perform(click());
        }

        doTestLogin();
        onView(withId(R.id.buttonAdd)).perform(click());

        onView(withId(R.id.txt_title_create_offer)).perform(typeText("test title"));
        closeSoftKeyboard();
        onView(withId(R.id.txt_description_create_offer)).perform(typeText("Test Test Test jj"));
        closeSoftKeyboard();
        onView(withId(R.id.txt_price_create_offer)).perform(typeText("33"));
        closeSoftKeyboard();
        onView(withId(R.id.btnMenuAdd)).perform(click());
        webService.DeleteOfferTest();
    }

    @Test
    public void addAOfferWithoutDescription() throws  Exception {
        if(HomeActivity.currentUser.getUserId() != Constants.NOT_LOGGED_IN) {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Logout")).perform(click());
        }

        doTestLogin();
        onView(withId(R.id.buttonAdd)).perform(click());

        onView(withId(R.id.txt_title_create_offer)).perform(typeText("Analysis 1"));
        closeSoftKeyboard();
        onView(withId(R.id.btn_creat_offer)).perform(click());
    }
    @Test
    public void addAOfferWithoutTitle() throws  Exception {
        if(HomeActivity.currentUser.getUserId() != Constants.NOT_LOGGED_IN) {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Logout")).perform(click());
        }

        doTestLogin();
        onView(withId(R.id.buttonAdd)).perform(click());

        onView(withId(R.id.txt_description_create_offer)).perform(typeText("Nachhilfestunde Montags 9:00 im LZ 2"));
        closeSoftKeyboard();
        onView(withId(R.id.btnMenuAdd)).perform(click());
    }

    public void doTestLogin() {
        onView(withId(R.id.btnMenuLogin)).perform(click());
        onView(withId(R.id.txtUserName)).perform(typeText("testpic@gmail.com"));
        onView(withId(R.id.txtPassword)).perform(typeText("1234"));
        onView(withId(R.id.btnLogin)).perform(click());
    }
}

