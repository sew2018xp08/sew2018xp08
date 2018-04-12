package com.educationaid.tutoring;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class LoginInstrumentedTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new
            ActivityTestRule<>(LoginActivity.class);

    @Test
    public void useAppContext() throws Exception {

        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.educationaid.tutoring", appContext.getPackageName());
    }

    @Test
    public void checkButtonLogin() {
        onView(withId(R.id.btnLogin)).perform(click());
    }

    @Test
    public void checkButtonRegister() {
        onView(withId(R.id.btnRegister)).perform(click());
    }

    @Test
    public void checkTextFields() {
        onView(withId(R.id.txtUserName)).perform(typeText("john@example.com"));
        onView(withId(R.id.txtPassword)).perform(typeText("1234"));
    }

    @Test
    public void checkWrongLogin() {
        onView(withId(R.id.txtUserName)).perform(typeText("john@exampleWrong.com"));
        onView(withId(R.id.txtPassword)).perform(typeText("1234"));
        onView(withId(R.id.btnLogin)).perform(click());
    }

    @Test
    public void checkRightLogin() {
        onView(withId(R.id.txtUserName)).perform(typeText("john@example.com"));
        onView(withId(R.id.txtPassword)).perform(typeText("1234"));
        onView(withId(R.id.btnLogin)).perform(click());
    }
}
