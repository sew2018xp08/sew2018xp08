package com.educationaid.tutoring;

import com.educationaid.tutoring.Constants.Constants;
import com.educationaid.tutoring.WebService.WebService;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class PHPWebserviceUnitTest extends Mockito {

    @Test
    public void PHPLoginTest() throws Exception {

        String result = WebService.Login("john@example.com", "1234");
        Assert.assertTrue(result.equals(Constants.ANS_RIGHT_USERNAME_PASSWORD));
    }

    @Test
    public void PHPRegisterTest() throws Exception {
        Assert.assertTrue(WebService.DeleteTestUserFromDataBase().equals(Constants.ANS_DELETE_USER));
        String result = WebService.Register("Max", "Mustermann", "test@test.com", "123");
        Assert.assertTrue(result.equals(Constants.ANS_CREATED_USER_SUCCESFULLY));
        Assert.assertTrue(WebService.DeleteTestUserFromDataBase().equals(Constants.ANS_DELETE_USER));
    }
}