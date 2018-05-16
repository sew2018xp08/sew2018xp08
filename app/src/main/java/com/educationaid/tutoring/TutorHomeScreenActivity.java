package com.educationaid.tutoring;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.educationaid.tutoring.Constants.Constants;
import com.educationaid.tutoring.Model.User;
import com.educationaid.tutoring.adapters.OfferListAdapter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TutorHomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tutor_home_screen);
        setTitle("Home");




        final RecyclerView recyclerView = findViewById(R.id.recyclerView);

        ((TextView)findViewById(R.id.welcomeText)).setText("Hello " + HomeActivity.currentUser.getFirstName() + " " + HomeActivity.currentUser.getLastName() + ".");

        OfferListAdapter.RecyclerViewClickListener listener = (view, position) -> {
            Toast.makeText(view.getContext(), "Position " + position, Toast.LENGTH_SHORT).show();
        };
        ArrayList<Pair<String, String>> offers = buildList();

        @SuppressLint("ResourceType")
        OfferListAdapter oladapter = new OfferListAdapter(this, offers);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(oladapter);

        FloatingActionButton btnAdd = findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(v -> {
            Intent myIntent = new Intent(TutorHomeScreenActivity.this, CreateOfferActivity.class);
            TutorHomeScreenActivity.this.startActivity(myIntent);
        });
    }

    public ArrayList<Pair<String, String>> buildList() {
        String offersString = getOffers(Integer.toString(HomeActivity.currentUser.getUserId()));
        ArrayList<Pair<String, String>> offers = new ArrayList<>();

        try {
            JSONArray jsonArrayOffers = new JSONArray(offersString);
            for (int i = 0; i < jsonArrayOffers.length(); i++) {
                String id = jsonArrayOffers.getJSONObject(i).getString("o_id");
                String title = jsonArrayOffers.getJSONObject(i).getString("title");
                offers.add(new Pair<>(id, title));
            }
            return offers;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_custom_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_button:
                HomeActivity.currentUser = new User();
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public String getOffers(String tutorID) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String resturn_statement;
                String paramTutorID = params[0];

                HttpClient httpClient = new DefaultHttpClient();


                HttpPost httpPost = new HttpPost(Constants.PHP_VIEW_OFFER);

                BasicNameValuePair tutorIDBasicNameValuePair = new BasicNameValuePair(Constants.POST_ID_UID, paramTutorID);

                // We add the content that we want to pass with the POST request to as name-value pairs
                //Now we put those sending details to an ArrayList with type safe of NameValuePair
                List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
                nameValuePairList.add(tutorIDBasicNameValuePair);

                try {
                    // UrlEncodedFormEntity is an entity composed of a list of url-encoded pairs.
                    //This is typically useful while sending an HTTP POST request.
                    UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairList);

                    // setEntity() hands the entity (here it is urlEncodedFormEntity) to the request.
                    httpPost.setEntity(urlEncodedFormEntity);

                    try {
                        // HttpResponse is an interface just like HttpPost.
                        //Therefore we can't initialize them
                        HttpResponse httpResponse = httpClient.execute(httpPost);
                        HttpEntity entity = httpResponse.getEntity();
                        resturn_statement = EntityUtils.toString(entity).toString();
                        return resturn_statement;

                    } catch (ClientProtocolException cpe) {
                        System.out.println("First Exception caz of HttpResponese :" + cpe);
                        cpe.printStackTrace();
                    } catch (IOException ioe) {
                        System.out.println("Second Exception caz of HttpResponse :" + ioe);
                        ioe.printStackTrace();
                    }

                } catch (UnsupportedEncodingException uee) {
                    System.out.println("An Exception given because of UrlEncodedFormEntity argument :" + uee);
                    uee.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        try {
            return sendPostReqAsyncTask.execute(tutorID).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
