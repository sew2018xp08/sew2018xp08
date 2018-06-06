package com.educationaid.tutoring;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.educationaid.tutoring.Constants.Constants;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CreateOfferActivity extends AppCompatActivity implements View.OnClickListener{
    private String price = null;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnMenuAdd:
                createOffer();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_create_offer, menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_offer);
        setTitle("New Offer");
        ((Button)findViewById(R.id.btn_creat_offer)).setOnClickListener(this);
    }

    public void insertOffer(String title, String description, String tutorID) {

        class SendPostReqAsyncTask extends AsyncTask<String, String, String> {

            @Override
            protected String doInBackground(String... params) {

                String paramTutorID = params[0];
                String paramTitle = params[1];
                String paramDescription = params[2];
                String paramPrice = price;

                HttpClient httpClient = new DefaultHttpClient();

                HttpPost httpPost = new HttpPost(Constants.PHP_NEW_OFFER);

                BasicNameValuePair tutorIDBasicNameValuePair = new BasicNameValuePair(Constants.POST_ID_UID, paramTutorID);
                BasicNameValuePair titleBasicNameValuePair = new BasicNameValuePair(Constants.POST_ID_OFFER_TITLE, paramTitle);
                BasicNameValuePair descriptionBasicNameValuePAir = new BasicNameValuePair(Constants.POST_ID_OFFER_DESCRIPTION, paramDescription);
                BasicNameValuePair priceBasicNameValuePAir = new BasicNameValuePair(Constants.POST_ID_PRICE, paramPrice);

                List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
                nameValuePairList.add(tutorIDBasicNameValuePair);
                nameValuePairList.add(titleBasicNameValuePair);
                nameValuePairList.add(descriptionBasicNameValuePAir);
                nameValuePairList.add(priceBasicNameValuePAir);

                try {
                    UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairList);

                    httpPost.setEntity(urlEncodedFormEntity);

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

                } catch (UnsupportedEncodingException uee) {
                    System.out.println("An Exception given because of UrlEncodedFormEntity argument :" + uee);
                    uee.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                if (result.equals(Constants.ANS_CREATE_OFFER)) {
                    Toast.makeText(CreateOfferActivity.this, "Offer created successfully",
                            Toast.LENGTH_LONG).show();
                    startActivity(new Intent(CreateOfferActivity.this, TutorHomeScreenActivity.class));

                } else {
                    System.out.println("Invalid POST req...");
                }
            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(tutorID, title, description);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_creat_offer:
                createOffer();
                break;
        }
    }

    public void createOffer() {
        EditText editTitle = (EditText) findViewById(R.id.txt_title_create_offer);
        EditText descriptionTitle = (EditText) findViewById(R.id.txt_description_create_offer);
        EditText priceTitle = (EditText) findViewById(R.id.txt_price_create_offer);

        if (editTitle.getText().toString().equals("")) {
            editTitle.setError("No Title");
        }
        if (descriptionTitle.getText().toString().equals("")) {
            descriptionTitle.setError("No Description");
        }

        if(priceTitle.getText().toString().equals("")) {
            priceTitle.setError("No Price");
        }

        if ((!editTitle.getText().toString().equals(""))
                && !(descriptionTitle.getText().toString().equals("")) &&
                !(priceTitle.getText().toString().equals(""))) {
            // send database request
            price = priceTitle.getText().toString();
            insertOffer(editTitle.getText().toString(), descriptionTitle.getText().toString(), Integer.toString(HomeActivity.currentUser.getUserId()));
        }
    }
}
