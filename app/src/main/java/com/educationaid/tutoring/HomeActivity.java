package com.educationaid.tutoring;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.educationaid.tutoring.Constants.Constants;
import com.educationaid.tutoring.Model.User;

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

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    public static User currentUser = new User();
    private static ArrayList<TableRow> offers_in_table_;

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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(currentUser.getAdmin() == Constants.NOT_LOGGED_IN ? R.menu.menu_home_view_unlogged : R.menu.menu_home_view_loggedin, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        System.out.println(currentUser.getUserId());
        offers_in_table_ = new ArrayList<>();
        getOffers();
    }

    private void setRowListener() {
        for(TableRow row : offers_in_table_) {
            row.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnLogin:
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                break;
            default:
                for(TableRow row : offers_in_table_) {
                    if(v.getId() == row.getId()) {
                        System.out.println(row.getId());
                        Intent intent = new Intent(HomeActivity.this, OfferDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("offerid", row.getId());
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    }
                }
        }
    }

    public void getOffers() {

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

                        String bufferedStrChunk = null;

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


                    try {
                        JSONArray obj = new JSONArray(result);
                        System.out.println("Hei sweetty <3");

                        TableLayout table = new TableLayout(HomeActivity.this);
                        TableLayout.LayoutParams tableRowParams=
                                new TableLayout.LayoutParams
                                        (TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.FILL_PARENT);
                        tableRowParams.setMargins(0,10,0,10);
                        table.setLayoutParams(tableRowParams);
                        table.setBackgroundColor(Color.LTGRAY);

                        LinearLayout linearLayout = new LinearLayout(HomeActivity.this);
                        setContentView(linearLayout);
                        linearLayout.setOrientation(LinearLayout.VERTICAL);

                        TextView txtOffers = new TextView(HomeActivity.this);
                        txtOffers.setTextSize(30);
                        txtOffers.setText("Offers");
                        linearLayout.addView(txtOffers);


                        for(int i = 0; i < obj.length(); i++)
                        {
                            TableRow row = new TableRow(HomeActivity.this);
                            row.setClickable(true);
                            row.setId(Integer.parseInt(obj.getJSONObject(i).getString("o_id")));
                            row.setBackgroundColor(((i%2) == 0) ? Color.DKGRAY : Color.GRAY);
                            row.setLayoutParams(tableRowParams);
                            for(int j = 0; j < 3; j++) {
                                TextView tv = new TextView(HomeActivity.this);
                                tv.setTextSize(15);
                                if(j == 1) {
                                    tv.setText("         |         ");
                                    row.addView(tv);
                                    continue;
                                }
                                tv.setText(j == 0 ? obj.getJSONObject(i).getString("title") : obj.getJSONObject(i).getString("first_name") + " " + obj.getJSONObject(i).getString("last_name"));
                                row.addView(tv);
                                offers_in_table_.add(row);
                            }
                            table.addView(row);
                        }
                        linearLayout.addView(table);
                        setRowListener();

                        HomeActivity.currentUser = new User(Integer.valueOf(obj.getJSONObject(0).getString("u_id")), obj.getJSONObject(0).getString("first_name"),
                                obj.getJSONObject(0).getString("last_name"), false, obj.getJSONObject(0).getString("email"),
                                Integer.valueOf(obj.getJSONObject(0).getString("admin")), null);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();
    }
}
