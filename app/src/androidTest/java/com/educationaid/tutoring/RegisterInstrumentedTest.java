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
    public void testLicenseInfo() {
        onView(withId(R.id.btnLicenceInfo)).perform(click());
        closeSoftKeyboard();
    }

    @Test
    public void testOnlyNameEmpty() {
        onView(withId(R.id.rbProTutor)).perform(click());
        onView(withId(R.id.txtSurnameRegistry)).perform(scrollTo(), typeText("Mustermann"));
        onView(withId(R.id.txtEmailRegistry)).perform(scrollTo(), typeText("test@test.com"));
        onView(withId(R.id.txtPasswordRegistry)).perform(scrollTo(), typeText("123"));
        onView(withId(R.id.txtPasswordconfRegistry)).perform(scrollTo(), typeText("123"));
        closeSoftKeyboard();
        onView(withId(R.id.btnRegistry)).perform(scrollTo(), click());
    }

    @Test
    public void testOnlySurNameEmpty() {
        onView(withId(R.id.rbProTutor)).perform(click());
        onView(withId(R.id.txtNameRegistry)).perform(scrollTo(), typeText("Max"));
        onView(withId(R.id.txtEmailRegistry)).perform(scrollTo(), typeText("test@test.com"));
        onView(withId(R.id.txtPasswordRegistry)).perform(scrollTo(), typeText("123"));
        onView(withId(R.id.txtPasswordconfRegistry)).perform(scrollTo(), typeText("123"));
        closeSoftKeyboard();
        onView(withId(R.id.btnRegistry)).perform(scrollTo(), click());
    }

    @Test
    public void testOnlyEmailEmpty() {
        onView(withId(R.id.rbProTutor)).perform(click());
        onView(withId(R.id.txtNameRegistry)).perform(scrollTo(), typeText("Max"));
        onView(withId(R.id.txtSurnameRegistry)).perform(scrollTo(), typeText("Mustermann"));
        onView(withId(R.id.txtPasswordRegistry)).perform(scrollTo(), typeText("123"));
        onView(withId(R.id.txtPasswordconfRegistry)).perform(scrollTo(), typeText("123"));
        closeSoftKeyboard();
        onView(withId(R.id.btnRegistry)).perform(scrollTo(), click());
    }

    @Test
    public void testOnlyPasswort() {
        onView(withId(R.id.rbProTutor)).perform(click());
        onView(withId(R.id.txtNameRegistry)).perform(scrollTo(), typeText("Max"));
        onView(withId(R.id.txtSurnameRegistry)).perform(scrollTo(), typeText("Mustermann"));
        onView(withId(R.id.txtEmailRegistry)).perform(scrollTo(), typeText("test@test.com"));
        onView(withId(R.id.txtPasswordconfRegistry)).perform(scrollTo(), typeText("123"));
        closeSoftKeyboard();
        onView(withId(R.id.btnRegistry)).perform(scrollTo(), click());
    }

    @Test
    public void testOnlyPasswortSecond() {
        onView(withId(R.id.rbProTutor)).perform(click());
        onView(withId(R.id.txtNameRegistry)).perform(scrollTo(), typeText("Max"));
        onView(withId(R.id.txtSurnameRegistry)).perform(scrollTo(), typeText("Mustermann"));
        onView(withId(R.id.txtEmailRegistry)).perform(scrollTo(), typeText("test@test.com"));
        onView(withId(R.id.txtPasswordRegistry)).perform(scrollTo(), typeText("123"));
        closeSoftKeyboard();
        onView(withId(R.id.btnRegistry)).perform(scrollTo(), click());
    }

    @Test
    public void testdifferentPassword() {
        onView(withId(R.id.rbProTutor)).perform(click());
        onView(withId(R.id.txtNameRegistry)).perform(scrollTo(), typeText("Max"));
        onView(withId(R.id.txtSurnameRegistry)).perform(scrollTo(), typeText("Mustermann"));
        onView(withId(R.id.txtEmailRegistry)).perform(scrollTo(), typeText("test@test.com"));
        onView(withId(R.id.txtPasswordRegistry)).perform(scrollTo(), typeText("123"));
        onView(withId(R.id.txtPasswordconfRegistry)).perform(scrollTo(), typeText("1234"));
        closeSoftKeyboard();
        onView(withId(R.id.btnRegistry)).perform(scrollTo(), click());
    }

   @Test
    public void testRegisterNewUser() {
        onView(withId(R.id.rbProTutor)).perform(click());
        onView(withId(R.id.txtNameRegistry)).perform(scrollTo(), typeText("Max"));
        onView(withId(R.id.txtSurnameRegistry)).perform(scrollTo(), typeText("Mustermann"));
        onView(withId(R.id.txtEmailRegistry)).perform(scrollTo(), typeText("test@test.com"));
        onView(withId(R.id.txtPasswordRegistry)).perform(scrollTo(), typeText("123"));
        onView(withId(R.id.txtPasswordconfRegistry)).perform(scrollTo(), typeText("123"));

        closeSoftKeyboard();
        onView(withId(R.id.btnRegistry)).perform(click());

        Assert.assertTrue(webService.DeleteTestUserFromDataBase().equals(Constants.ANS_DELETE_USER));
    }

    @Test
    public void testRegisterNewUserAlreadyExists() {
        onView(withId(R.id.rbProTutor)).perform(click());
        onView(withId(R.id.txtNameRegistry)).perform(scrollTo(), typeText("testdobule"));
        onView(withId(R.id.txtSurnameRegistry)).perform(scrollTo(), typeText("testdobule"));
        onView(withId(R.id.txtEmailRegistry)).perform(scrollTo(), typeText("testdouble@test.com"));
        onView(withId(R.id.txtPasswordRegistry)).perform(scrollTo(), typeText("123"));
        onView(withId(R.id.txtPasswordconfRegistry)).perform(scrollTo(), typeText("123"));

        closeSoftKeyboard();
        onView(withId(R.id.btnRegistry)).perform(click());

        if(HomeActivity.currentUser.getUserId() != Constants.NOT_LOGGED_IN) {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Logout")).perform(click());
        }
        onView(withId(R.id.btnMenuLogin)).perform(click());
        onView(withId(R.id.btnRegister)).perform(click());

        onView(withId(R.id.rbProTutor)).perform(click());
        onView(withId(R.id.txtNameRegistry)).perform(scrollTo(), typeText("testdobule"));
        onView(withId(R.id.txtSurnameRegistry)).perform(scrollTo(), typeText("testdobule"));
        onView(withId(R.id.txtEmailRegistry)).perform(scrollTo(), typeText("testdouble@test.com"));
        onView(withId(R.id.txtPasswordRegistry)).perform(scrollTo(), typeText("123"));
        onView(withId(R.id.txtPasswordconfRegistry)).perform(scrollTo(), typeText("123"));

        closeSoftKeyboard();
        onView(withId(R.id.btnRegistry)).perform(click());
        Assert.assertTrue(webService.DeleteTestUserFromDataBase().equals(Constants.ANS_DELETE_USER));
    }
}
