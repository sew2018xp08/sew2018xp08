package com.educationaid.tutoring;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        (findViewById(R.id.btnRegistry)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnRegistry){
            if(registryValidation()) {
                doRegister(((TextView)findViewById(R.id.txtNameRegistry)).getText().toString(), ((TextView)findViewById(R.id.txtSurnameRegistry)).getText().toString(),
                        ((TextView)findViewById(R.id.txtEmailRegistry)).getText().toString(), ((TextView)findViewById(R.id.txtPasswordRegistry)).getText().toString());
                } else
                Toast.makeText(RegisterActivity.this, "Check input",
                        Toast.LENGTH_LONG).show();
            }
        }


    private Boolean registryValidation() {
        if(((TextView)findViewById(R.id.txtNameRegistry)).getText().toString().equals(""))
        {
            ((TextView)findViewById(R.id.lblErrorNameRegistry)).setVisibility(View.VISIBLE);
            return false;
        }
        else
        {
            ((TextView)findViewById(R.id.lblErrorNameRegistry)).setVisibility(View.INVISIBLE);
        }

        if(((TextView)findViewById(R.id.txtSurnameRegistry)).getText().toString().equals("")){
            ((TextView)findViewById(R.id.lblErrorSurnameRegistry)).setVisibility(View.VISIBLE);
            return false;
        }
        else
        {
            ((TextView)findViewById(R.id.lblErrorSurnameRegistry)).setVisibility(View.INVISIBLE);
        }

        if(((TextView)findViewById(R.id.txtEmailRegistry)).getText().toString().equals("") ||
                android.util.Patterns.EMAIL_ADDRESS.matcher(((TextView)findViewById(R.id.txtEmailRegistry)).getText().toString()).matches())
        {
            ((TextView)findViewById(R.id.lblErrorEmailRegistry)).setVisibility(View.VISIBLE);
            return false;
        }
        else
        {
            ((TextView)findViewById(R.id.lblErrorEmailRegistry)).setVisibility(View.INVISIBLE);
        }

        if(((TextView)findViewById(R.id.txtPasswordRegistry)).getText().toString().equals("")){
            ((TextView)findViewById(R.id.lblErrorPasswordRegistry)).setText("Darf nicht leer sein!");
            ((TextView)findViewById(R.id.lblErrorPasswordRegistry)).setVisibility(View.VISIBLE);
            return false;
        }
        else
        {
            ((TextView)findViewById(R.id.lblErrorPasswordRegistry)).setVisibility(View.INVISIBLE);
        }

        if(((TextView)findViewById(R.id.txtPasswordconfRegistry)).getText().toString().equals("")){
            ((TextView)findViewById(R.id.lblErrorPasswordconfRegistry)).setText("Darf nicht leer sein!");
            ((TextView)findViewById(R.id.lblErrorPasswordconfRegistry)).setVisibility(View.VISIBLE);
            return false;
        }
        else
        {
            ((TextView)findViewById(R.id.lblErrorPasswordconfRegistry)).setVisibility(View.INVISIBLE);
        }

        if(!(((TextView)findViewById(R.id.txtPasswordRegistry)).getText().toString().equals(((TextView)findViewById(R.id.txtPasswordconfRegistry)).getText().toString()))){
            ((TextView)findViewById(R.id.lblErrorPasswordRegistry)).setText("Passwörter stimmen nicht überein!");
            ((TextView)findViewById(R.id.lblErrorPasswordRegistry)).setVisibility(View.VISIBLE);
            ((TextView)findViewById(R.id.lblErrorPasswordconfRegistry)).setText("Passwörter stimmen nicht überein!");
            ((TextView)findViewById(R.id.lblErrorPasswordconfRegistry)).setVisibility(View.VISIBLE);
            return false;
        }
        else
        {
            ((TextView)findViewById(R.id.lblErrorPasswordRegistry)).setVisibility(View.INVISIBLE);
            ((TextView)findViewById(R.id.lblErrorPasswordconfRegistry)).setVisibility(View.INVISIBLE);
        }
        return true;
    }

    public void doRegister(String givenFirstname, String givenLastname, String givenEmail, String givenPassword) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String paramFirstname = params[0];
                String paramLastname = params[1];
                String paramEmail = params[2];
                String paramPassword = params[3];


                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(Constants.PHP_REGISTER_LINK);

                BasicNameValuePair emailBasicNameValuePair = new BasicNameValuePair(Constants.POST_ID_EMAIL, paramEmail);
                BasicNameValuePair passwordBasicNameValuePAir = new BasicNameValuePair(Constants.POST_ID_PASSWORD, paramPassword);
                BasicNameValuePair firstnameBasicNameValuePAir = new BasicNameValuePair(Constants.POST_ID_FIRSTNAME, paramFirstname);
                BasicNameValuePair lastnameBasicNameValuePAir = new BasicNameValuePair(Constants.POST_ID_LASTNAME, paramLastname);

                List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
                nameValuePairList.add(emailBasicNameValuePair);
                nameValuePairList.add(passwordBasicNameValuePAir);
                nameValuePairList.add(firstnameBasicNameValuePAir);
                nameValuePairList.add(lastnameBasicNameValuePAir);

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
                } catch (UnsupportedEncodingException uee) {
                    System.out.println("An Exception given because of UrlEncodedFormEntity argument :" + uee);
                    uee.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                if(result.equals(Constants.ANS_CREATED_USER_SUCCESFULLY)){
                    startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                    Toast.makeText(getApplicationContext(), R.string.successful_registry, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), R.string.user_alredy_exists, Toast.LENGTH_LONG).show();
                }
            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(givenFirstname, givenLastname, givenEmail, givenPassword);
    }
}
