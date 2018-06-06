package com.educationaid.tutoring;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TableRow;

import com.educationaid.tutoring.Constants.Constants;
import com.educationaid.tutoring.Model.Offer;
import com.educationaid.tutoring.Model.User;
import com.educationaid.tutoring.adapters.OfferListAdapter;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class HomeActivity extends AppCompatActivity {
    public static User currentUser = new User();
    private static ArrayList<TableRow> offers_in_table_;
    private ArrayList<Pair<String, String>> offers = new ArrayList<>();


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnMenuLogin:
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                return true;
            case R.id.btnMenuLogout:
                currentUser = new User();
                Intent refresh = new Intent(this, HomeActivity.class);
                startActivity(refresh);
                this.finish();
                return true;
            case R.id.btnMenuTutorHomeView:
                startActivity(new Intent(HomeActivity.this, TutorHomeScreenActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(currentUser == null)
            currentUser = new User();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(currentUser.getAdmin() == Constants.NOT_LOGGED_IN ? R.menu.menu_home_view_unlogged : R.menu.menu_home_view_loggedin, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ArrayList<Offer> offers = buildList();

        @SuppressLint("ResourceType")
        OfferListAdapter oladapter = new OfferListAdapter(this, offers, true);

        final RecyclerView recyclerView = findViewById(R.id.offerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(oladapter);
    }

    private ArrayList<Offer> buildList() {
        ArrayList<Offer> offerList = new ArrayList<>();
        String offerString = getOffers();
        try {
            JSONArray jsonArrayOffers = new JSONArray(offerString);
            for (int i = 0; i < jsonArrayOffers.length(); i++) {
                String id = jsonArrayOffers.getJSONObject(i).getString("o_id");
                String title = jsonArrayOffers.getJSONObject(i).getString("title");
                String forename = jsonArrayOffers.getJSONObject(i).getString("first_name");
                String lastname = jsonArrayOffers.getJSONObject(i).getString("last_name");
                String isPro = jsonArrayOffers.getJSONObject(i).getString("pro");

                offerList.add(new Offer(id, title, forename, lastname, isPro));
            }
            return offerList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

//    @Override
//    public void onClick(View v) {
//        switch(v.getId()) {
//            case R.id.btnLogin:
//                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
//                break;
//            default:
//                for(TableRow row : offers_in_table_) {
//                    if(v.getId() == row.getId()) {
//                        System.out.println(row.getId());
//                        Intent intent = new Intent(HomeActivity.this, OfferDetailActivity.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putInt("offerid", row.getId());
//                        intent.putExtras(bundle);
//                        startActivity(intent);
//                        break;
//                    }
//                }
//        }
//    }

    public String getOffers() {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(Constants.PHP_VIEW_ALL_OFFERS);
                try {
                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    InputStream inputStream = httpResponse.getEntity().getContent();

                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    StringBuilder stringBuilder = new StringBuilder();

                    String bufferedStrChunk;

                    while ((bufferedStrChunk = bufferedReader.readLine()) != null) {
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
            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        try {
            return sendPostReqAsyncTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
