package com.educationaid.tutoring;

import com.educationaid.tutoring.Constants.Constants;
import com.educationaid.tutoring.WebService.WebService;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class PHPWebserviceUnitTest extends Mockito {

    WebService webService;

    @Before
    public void Setup() {
        webService = new WebService();
    }

    @Test
    public void phpLoginTest() throws Exception {
        Assert.assertTrue(!webService.Login("john@example.com", "1234").equals(Constants.ANS_RIGHT_USERNAME_PASSWORD));
    }

    @Test
    public void phpRegisterTest() throws Exception {
        Assert.assertTrue(webService.DeleteTestUserFromDataBase().equals(Constants.ANS_DELETE_USER));
        String result = webService.Register("Max", "Mustermann", "test@test.com", "123", "0");
        Assert.assertTrue(result.equals(Constants.ANS_CREATED_USER_SUCCESFULLY));
        Assert.assertTrue(webService.DeleteTestUserFromDataBase().equals(Constants.ANS_DELETE_USER));
    }

    @Test
    public void phpCreateDeleteOfferTest() throws Exception {
        //Assert.assertTrue(!webService.Login("john@example.com", "1234").equals(Constants.ANS_RIGHT_USERNAME_PASSWORD));
    }
}