package com.educationaid.tutoring;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by kevin on 11.04.2018.
 */
@RunWith(AndroidJUnit4.class)
public class RegisterInstrumentedTest {
    @Rule
    public ActivityTestRule<RegisterActivity> mActivityRule = new
            ActivityTestRule<>(RegisterActivity.class);


    @Test
    public void testRegister() {
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

        onView(withId(R.id.btnRegistry)).perform(click());

    }
}
