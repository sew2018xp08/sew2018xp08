package com.educationaid.tutoring;

import com.educationaid.tutoring.Constants.Constants;
import com.educationaid.tutoring.WebService.WebService;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PHPWebserviceTest  {

    @Test
    public void PHPLogin() throws Exception {
        String result = WebService.Login("john@example.com", "1234");
        if(result.equals(Constants.ANS_RIGHT_USERNAME_PASSWORD)){
            System.out.println("HTTP POST is working...");
        }else{
            System.out.println("Invalid POST req...");
        }
    }
}