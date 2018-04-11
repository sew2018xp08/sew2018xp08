package com.educationaid.tutoring;

import android.os.AsyncTask;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.educationaid.tutoring.Constants.Constants;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
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

        closeSoftKeyboard();
        onView(withId(R.id.txtPasswordconfRegistry)).perform(scrollTo(), click());
    }

    public static void deleteTestUserFromDataBase() {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                HttpClient httpClient = new DefaultHttpClient();

                // In a POST request, we don't pass the values in the URL.
                //Therefore we use only the web page URL as the parameter of the HttpPost argument
                HttpPost httpPost = new HttpPost(Constants.PHP_TEST_DELETE_USER);
                    try {
                        // HttpResponse is an interface just like HttpPost.
                        //Therefore we can't initialize them
                        HttpResponse httpResponse = httpClient.execute(httpPost);

                        // According to the JAVA API, InputStream constructor do nothing.
                        //So we can't initialize InputStream although it is not an interface
                        InputStream inputStream = httpResponse.getEntity().getContent();

                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                        StringBuilder stringBuilder = new StringBuilder();

                        String bufferedStrChunk = null;

                        while((bufferedStrChunk = bufferedReader.readLine()) != null){
                            stringBuilder.append(bufferedStrChunk);
                        }

                        return stringBuilder.toString();

                    } catch (ClientProtocolException cpe) {
                        System.out.println("First Exception caz of HttpResponese :" + cpe);
                        cpe.printStackTrace();
                    } catch (IOException ioe) {
                        System.out.println("Second Exception caz of HttpResponse :" + ioe);
                        ioe.printStackTrace();
                    }


                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                if(result.equals(Constants.ANS_DELETE_USER)){
                    System.out.println("HTTP POST is working...");
                }else{
                    System.out.println("Invalid POST req...");
                }
            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();
    }
}
