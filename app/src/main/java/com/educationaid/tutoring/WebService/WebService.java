package com.educationaid.tutoring.WebService;

import com.educationaid.tutoring.Constants.Constants;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WebService  {

    public static String Login(String paramEmail, String paramPassword) {

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(Constants.PHP_LOGIN_LIMK);

        try
        {
            httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(
                    new BasicNameValuePair(Constants.POST_ID_EMAIL, paramEmail),
                    new BasicNameValuePair(Constants.POST_ID_PASSWORD, paramPassword)
            )));

            try {
                return EntityUtils.toString(httpClient.execute(httpPost).getEntity(), "UTF-8");

            } catch (ClientProtocolException cpe) {
                System.out.println("First Exception caz of HttpResponese :" + cpe);
                cpe.printStackTrace();
            } catch (IOException ioe) {
                System.out.println("Second Exception caz of HttpResponse :" + ioe);
                ioe.printStackTrace();
            }

        } catch (
                UnsupportedEncodingException uee)

        {
            System.out.println("An Exception given because of UrlEncodedFormEntity argument :" + uee);
            uee.printStackTrace();
        }

        return null;
    }

    public static String Register(String paramFirstname, String paramLastname, String paramEmail, String paramPassword) {
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

    public static String DeleteTestUserFromDataBase() {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(Constants.PHP_TEST_DELETE_USER);
        try {
            return EntityUtils.toString(httpClient.execute(httpPost).getEntity(), "UTF-8");

        } catch (ClientProtocolException cpe) {
            System.out.println("First Exception caz of HttpResponese :" + cpe);
            cpe.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("Second Exception caz of HttpResponse :" + ioe);
            ioe.printStackTrace();
        }


        return null;
    }
}
