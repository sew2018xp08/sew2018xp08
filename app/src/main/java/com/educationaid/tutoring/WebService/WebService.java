package com.educationaid.tutoring.WebService;

import com.educationaid.tutoring.Constants.Constants;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class WebService {
    HttpClient httpClient;

    public WebService() {
        httpClient = new DefaultHttpClient();
    }

    public void SetEntity(HttpPost httpPost, List<BasicNameValuePair> params) {
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException uee) {
            System.out.println("An Exception given because of UrlEncodedFormEntity argument :" + uee);
            uee.printStackTrace();
        }
    }

    public String ExecuteResultToString(HttpUriRequest request) {
        try {
            return EntityUtils.toString(httpClient.execute(request).getEntity());
        } catch (ClientProtocolException cpe) {
            System.out.println("First Exception caz of HttpResponese :" + cpe);
            cpe.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("Second Exception caz of HttpResponse :" + ioe);
            ioe.printStackTrace();
        }
        return null;
    }

    public String Login(String paramEmail, String paramPassword) {
        HttpPost httpPost = new HttpPost(Constants.PHP_LOGIN_LIMK);
        SetEntity(httpPost, Arrays.asList(
                new BasicNameValuePair(Constants.POST_ID_EMAIL, paramEmail),
                new BasicNameValuePair(Constants.POST_ID_PASSWORD, paramPassword)
        ));
        return ExecuteResultToString(httpPost);
    }

    public String Register(String paramFirstname, String paramLastname, String paramEmail, String paramPassword) {
        HttpPost httpPost = new HttpPost(Constants.PHP_REGISTER_LINK);
        SetEntity(httpPost, Arrays.asList(
                new BasicNameValuePair(Constants.POST_ID_EMAIL, paramEmail),
                new BasicNameValuePair(Constants.POST_ID_PASSWORD, paramPassword),
                new BasicNameValuePair(Constants.POST_ID_FIRSTNAME, paramFirstname),
                new BasicNameValuePair(Constants.POST_ID_LASTNAME, paramLastname)
        ));
        return ExecuteResultToString(httpPost);
    }

    public String DeleteTestUserFromDataBase() {
        return ExecuteResultToString(new HttpPost(Constants.PHP_TEST_DELETE_USER));
    }

    public String DeleteOffer(String offerId) {
        HttpPost httpPost = new HttpPost(Constants.PHP_DELETE_OFFER);
        SetEntity(httpPost, Arrays.asList(
                new BasicNameValuePair(Constants.POST_ID_OFFER_ID, offerId)
        ));
        return ExecuteResultToString(httpPost);
    }
}
