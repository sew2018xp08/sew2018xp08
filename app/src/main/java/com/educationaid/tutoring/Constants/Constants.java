package com.educationaid.tutoring.Constants;

/**
 * Created by Kevin on 10.04.2018.
 */

public class Constants {
    //links
    public static final String PHP_LOGIN_LIMK = "http://xp082018.000webhostapp.com/PHP/login.php";
    public static final String PHP_REGISTER_LINK = "http://xp082018.000webhostapp.com/PHP/register.php";
    public static final String PHP_TEST_DELETE_USER = "http://xp082018.000webhostapp.com/PHP/test-delete_user.php";
    public static final String PHP_NEW_OFFER = "http://xp082018.000webhostapp.com/PHP/new_offer.php";
    public static final String PHP_VIEW_OFFER = "http://xp082018.000webhostapp.com/PHP/view_offers.php";
    public static final String PHP_VIEW_ALL_OFFERS = "http://xp082018.000webhostapp.com/PHP/view_all_offers.php";
    public static final String PHP_GET_OFFER_BY_OFFERID = "http://xp082018.000webhostapp.com/PHP/get_offer_by_offerid.php";
    public static final String PHP_PUSH_PICTURE = "http://xp082018.000webhostapp.com/PHP/uploadfile_kevin.php";


    //POST IDs
    public static final String POST_ID_EMAIL = "email";
    public static final String POST_ID_PASSWORD = "password";
    public static final String POST_ID_FIRSTNAME = "firstname";
    public static final String POST_ID_LASTNAME = "lastname";
    public static final String POST_ID_UID = "tutorID";
    public static final String POST_ID_OFFER_TITLE= "title";
    public static final String POST_ID_OFFER_DESCRIPTION = "description";
    public static final String POST_ID_OFFERID = "offerID";
    public static final String POST_ID_PRICE = "price";
    public static final String POST_ID_PRO = "pro";
    public static final String POST_PICTURE = "picture";


    //SERVER ANSWER - EQUALS
    public static final String ANS_CREATED_USER_SUCCESFULLY = "New record created successfully";
    public static final String ANS_RIGHT_USERNAME_PASSWORD = "WRONG INPUT";
    public static final String ANS_DELETE_USER = "test@test.com is deleted";
    public static final String ANS_CREATE_OFFER = "New offer created successfully";

    public static final int NOT_LOGGED_IN = 0;

    public static final int CAMERA = 100;
    public static final int GALLERY = 101;
}
