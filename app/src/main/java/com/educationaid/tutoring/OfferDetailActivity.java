package com.educationaid.tutoring;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.educationaid.tutoring.Constants.Constants;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class OfferDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private String email;
    private String price;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        ((Button) findViewById(R.id.ButtonContactDetail)).setOnClickListener(this);
        getOffer(String.valueOf(bundle.getInt("offerid")));
    }

    private void fillTextViews(JSONArray obj) throws JSONException {
        ((TextView) findViewById(R.id.txtTitleDetail)).setText(obj.getJSONObject(0).getString("title"));
        ((TextView) findViewById(R.id.txtDescriptionDetail)).setText(obj.getJSONObject(0).getString("description"));
        ((TextView) findViewById(R.id.textViewPriceDetail)).setText(obj.getJSONObject(0).getString("description") + " / Hour");
        email = obj.getJSONObject(0).getString("email");
        price = obj.getJSONObject(0).getString("price");
        title = obj.getJSONObject(0).getString("title");
    }

    public void getOffer(String offerid) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String paramOfferID = params[0];

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(Constants.PHP_GET_OFFER_BY_OFFERID);

                BasicNameValuePair offerIDBasicNameValuePair = new BasicNameValuePair(Constants.POST_ID_OFFERID, paramOfferID);

                List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
                nameValuePairList.add(offerIDBasicNameValuePair);

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
                try {
                    JSONArray obj = new JSONArray(result);
                    fillTextViews(obj);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(offerid);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ButtonContactDetail:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                String[] TO = {email};
                intent.putExtra(Intent.EXTRA_EMAIL, TO);
                intent.putExtra(Intent.EXTRA_SUBJECT, title);
                intent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(intent, title));
                break;

        }
    }
}
