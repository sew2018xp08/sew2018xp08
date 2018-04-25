package com.educationaid.tutoring;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.educationaid.tutoring.Constants.Constants;
import com.educationaid.tutoring.Model.User;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    public static User currentUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        System.out.println(currentUser.getUserId());
        ((Button)findViewById(R.id.btnLogin)).setOnClickListener(this);

        getOffers();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnLogin:
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                break;
        }
    }

    public void getOffers() {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(Constants.PHP_VIEW_ALL_OFFERS);
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

                        LinearLayout linearLayout = new LinearLayout(HomeActivity.this);
                        setContentView(linearLayout);
                        linearLayout.setOrientation(LinearLayout.VERTICAL);

                        TextView txtOffers = new TextView(HomeActivity.this);
                        txtOffers.setTextSize(30);
                        txtOffers.setText("Offers");
                        linearLayout.addView(txtOffers);
                        for(int i = 0; i < obj.length(); i++)
                        {
                            TextView textView = new TextView(HomeActivity.this);
                            textView.setText(obj.getJSONObject(i).getString("title") + " - " + obj.getJSONObject(i).getString("first_name") + " " + obj.getJSONObject(i).getString("last_name"));
                            linearLayout.addView(textView);
                        }

                        HomeActivity.currentUser = new User(Integer.valueOf(obj.getJSONObject(0).getString("u_id")), obj.getJSONObject(0).getString("first_name"),
                                obj.getJSONObject(0).getString("last_name"), obj.getJSONObject(0).getString("email"),
                                Integer.valueOf(obj.getJSONObject(0).getString("admin")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();
    }
}
