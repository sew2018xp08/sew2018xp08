package com.educationaid.tutoring;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        (findViewById(R.id.btnLogin)).setOnClickListener(this);
        (findViewById(R.id.btnRegister)).setOnClickListener(this);
        (findViewById(R.id.txtWarning)).setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;
        switch (clickedButton.getId()) {
            case R.id.btnLogin:
                (findViewById(R.id.txtWarning)).setVisibility(View.INVISIBLE);
                if((((EditText)findViewById(R.id.txtUserName)).getText().toString()).equals("") || (((EditText) findViewById(R.id.txtPassword)).getText().toString()).equals("")
                        || (!(((EditText)findViewById(R.id.txtUserName)).getText().toString()).contains("@")))
                {
                    (findViewById(R.id.txtWarning)).setVisibility(View.VISIBLE);
                    break;
                }

                doLogin(((EditText)findViewById(R.id.txtUserName)).getText().toString(), ((EditText)findViewById(R.id.txtPassword)).getText().toString());

                break;
            case R.id.btnRegister:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    public static void doLogin(String givenEmail, String givenPassword) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String paramEmail = params[0];
                String paramPassword = params[1];

                HttpClient httpClient = new DefaultHttpClient();

                // In a POST request, we don't pass the values in the URL.
                //Therefore we use only the web page URL as the parameter of the HttpPost argument
                HttpPost httpPost = new HttpPost(Constants.PHP_LOGIN_LIMK);

                // Because we are not passing values over the URL, we should have a mechanism to pass the values that can be
                //uniquely separate by the other end.
                //To achieve that we use BasicNameValuePair
                //Things we need to pass with the POST request
                BasicNameValuePair emailBasicNameValuePair = new BasicNameValuePair(Constants.POST_ID_EMAIL, paramEmail);
                BasicNameValuePair passwordBasicNameValuePAir = new BasicNameValuePair(Constants.POST_ID_PASSWORD, paramPassword);

                // We add the content that we want to pass with the POST request to as name-value pairs
                //Now we put those sending details to an ArrayList with type safe of NameValuePair
                List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
                nameValuePairList.add(emailBasicNameValuePair);
                nameValuePairList.add(passwordBasicNameValuePAir);

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

                } catch (UnsupportedEncodingException uee) {
                    System.out.println("An Exception given because of UrlEncodedFormEntity argument :" + uee);
                    uee.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                if(result.equals(Constants.ANS_RIGHT_USERNAME_PASSWORD)){
                    System.out.println("HTTP POST is working...");
                }else{
                    System.out.println("Invalid POST req...");
                }
            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(givenEmail, givenPassword);
    }
}
