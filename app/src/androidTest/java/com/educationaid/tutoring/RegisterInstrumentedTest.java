package com.educationaid.tutoring;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.educationaid.tutoring.Constants.Constants;
import com.educationaid.tutoring.WebService.WebService;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by kevin on 11.04.2018.
 */
@RunWith(AndroidJUnit4.class)
public class RegisterInstrumentedTest {
    WebService webService;
    @Before
    public void Init()
    {
        webService = new WebService();
        HomeActivity.currentUser = null;
    }
    @Rule
    public ActivityTestRule<RegistryActivity> mActivityRule = new
            ActivityTestRule<>(RegistryActivity.class);


    @Test
    public void testRegister() {
        Assert.assertTrue(webService.DeleteTestUserFromDataBase().equals(Constants.ANS_DELETE_USER));
        onView(withId(R.id.txtNameRegistry)).perform(scrollTo(), typeText("Max"));
        onView(withId(R.id.txtSurnameRegistry)).perform(scrollTo(), typeText("Mustermann"));
        onView(withId(R.id.txtEmailRegistry)).perform(scrollTo(), typeText("test@test.com"));
        onView(withId(R.id.txtPasswordRegistry)).perform(scrollTo(), typeText("123"));
        onView(withId(R.id.txtPasswordconfRegistry)).perform(scrollTo(), typeText("123"));

        onView(withId(R.id.txtNameRegistry)).check(matches(withText("Max")));
        onView(withId(R.id.txtSurnameRegistry)).check(matches(withText("Mustermann")));
        onView(withId(R.id.txtEmailRegistry)).check(matches(withText("test@test.com")));
        onView(withId(R.id.txtPasswordRegistry)).check(matches(withText("123")));
        onView(withId(R.id.txtPasswordconfRegistry)).check(matches(withText("123")));

        closeSoftKeyboard();
        onView(withId(R.id.btnRegistry)).perform(scrollTo(), click());
        onView(withText(R.string.successful_registry)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
        Assert.assertTrue(webService.DeleteTestUserFromDataBase().equals(Constants.ANS_DELETE_USER));
    }


    @Test
    public void testRegisterExistingUser() {
        Assert.assertTrue(webService.DeleteTestUserFromDataBase().equals(Constants.ANS_DELETE_USER));
        onView(withId(R.id.rbFreeTutor)).perform(click());
        onView(withId(R.id.txtNameRegistry)).perform(scrollTo(), typeText("Max"));
        onView(withId(R.id.txtSurnameRegistry)).perform(scrollTo(), typeText("Mustermann"));
        onView(withId(R.id.txtEmailRegistry)).perform(scrollTo(), typeText("test@test.com"));
        onView(withId(R.id.txtPasswordRegistry)).perform(scrollTo(), typeText("123"));
        onView(withId(R.id.txtPasswordconfRegistry)).perform(scrollTo(), typeText("123"));

        onView(withId(R.id.rbFreeTutor)).check(matches(isChecked()));
        onView(withId(R.id.rbProTutor)).check(matches(isNotChecked()));
        onView(withId(R.id.txtNameRegistry)).check(matches(withText("Max")));
        onView(withId(R.id.txtSurnameRegistry)).check(matches(withText("Mustermann")));
        onView(withId(R.id.txtEmailRegistry)).check(matches(withText("test@test.com")));
        onView(withId(R.id.txtPasswordRegistry)).check(matches(withText("123")));
        onView(withId(R.id.txtPasswordconfRegistry)).check(matches(withText("123")));

        closeSoftKeyboard();
        onView(withId(R.id.btnRegistry)).perform(scrollTo(), click());

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Login")).check(matches(isDisplayed()));
        onView(withText("Login")).perform(click());
        onView(withId(R.id.btnRegister)).perform(click());

        onView(withId(R.id.rbFreeTutor)).perform(click());
        onView(withId(R.id.txtNameRegistry)).perform(scrollTo(), typeText("Max"));
        onView(withId(R.id.txtSurnameRegistry)).perform(scrollTo(), typeText("Mustermann"));
        onView(withId(R.id.txtEmailRegistry)).perform(scrollTo(), typeText("test@test.com"));
        onView(withId(R.id.txtPasswordRegistry)).perform(scrollTo(), typeText("123"));
        onView(withId(R.id.txtPasswordconfRegistry)).perform(scrollTo(), typeText("123"));

        onView(withId(R.id.rbFreeTutor)).check(matches(isChecked()));
        onView(withId(R.id.rbProTutor)).check(matches(isNotChecked()));
        onView(withId(R.id.txtNameRegistry)).check(matches(withText("Max")));
        onView(withId(R.id.txtSurnameRegistry)).check(matches(withText("Mustermann")));
        onView(withId(R.id.txtEmailRegistry)).check(matches(withText("test@test.com")));
        onView(withId(R.id.txtPasswordRegistry)).check(matches(withText("123")));
        onView(withId(R.id.txtPasswordconfRegistry)).check(matches(withText("123")));

        closeSoftKeyboard();
        onView(withId(R.id.btnRegistry)).perform(scrollTo(), click());
        onView(withText(R.string.user_alredy_exists)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
        Assert.assertTrue(webService.DeleteTestUserFromDataBase().equals(Constants.ANS_DELETE_USER));
    }



}
