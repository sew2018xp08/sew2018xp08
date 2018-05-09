package com.educationaid.tutoring;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CreateOfferActivity extends AppCompatActivity {
    private String price = null;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnMenuAdd:
                final TextInputLayout editTitle = (TextInputLayout) findViewById(R.id.title_text);
                final TextInputLayout descriptionTitle = (TextInputLayout) findViewById(R.id.description_text);
                final TextInputLayout priceTitle = (TextInputLayout) findViewById(R.id.price_text);

                if (editTitle.getEditText().getText().toString().equals("")) {
                    editTitle.setError("No Title");
                }
                if (descriptionTitle.getEditText().getText().toString().equals("")) {
                    descriptionTitle.setError("No Description");
                }
                if ((!editTitle.getEditText().getText().toString().equals(""))
                        && !(descriptionTitle.getEditText().getText().toString().equals(""))) {
                    // send database request
                    price = priceTitle.getEditText().getText().toString();
                    insertOffer(editTitle.getEditText().getText().toString(), descriptionTitle.getEditText().getText().toString(), Integer.toString(HomeActivity.currentUser.getUserId()));
                }
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
                    Toast.makeText(CreateOfferActivity.this, "Offer created successfullyy",
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

}
