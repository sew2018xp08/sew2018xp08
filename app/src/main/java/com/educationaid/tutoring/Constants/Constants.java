package com.educationaid.tutoring.Constants;

/**
 * Created by Kevin on 10.04.2018.
 */

public class Constants {
    //links
    public static String PHP_LOGIN_LIMK = "http://sew2018xp08.bplaced.net/PHP/login.php";
    public static String PHP_REGISTER_LINK = "http://sew2018xp08.bplaced.net/PHP/register.php";
    public static String PHP_TEST_DELETE_USER = "http://sew2018xp08.bplaced.net/PHP/test-delete_user.php";
    public static String PHP_NEW_OFFER = "http://sew2018xp08.bplaced.net/PHP/new_offer.php";
    public static String PHP_VIEW_OFFER = "http://sew2018xp08.bplaced.net/PHP/view_offers.php";
    public static String PHP_DELETE_OFFER = "http://sew2018xp08.bplaced.net/PHP/delete_offer.php";
    public static String PHP_VIEW_ALL_OFFERS = "http://sew2018xp08.bplaced.net/PHP/view_all_offers.php";
    public static String PHP_GET_OFFER_BY_OFFERID = "http://sew2018xp08.bplaced.net/PHP/get_offer_by_offerid.php";


    //POST IDs
    public static String POST_ID_EMAIL = "email";
    public static String POST_ID_PASSWORD = "password";
    public static String POST_ID_FIRSTNAME = "firstname";
    public static String POST_ID_LASTNAME = "lastname";
    public static String POST_ID_UID = "tutorID";
    public static String POST_ID_OFFER_TITLE = "title";
    public static String POST_ID_OFFER_ID = "o_id";
    public static String POST_ID_OFFER_DESCRIPTION = "description";
    public static String POST_ID_OFFERID = "offerID";
    public static String POST_ID_PRICE = "price";


    //SERVER ANSWER - EQUALS
    public static String ANS_CREATED_USER_SUCCESFULLY = "New record created successfully";
    public static String ANS_RIGHT_USERNAME_PASSWORD = "WRONG INPUT";
    public static String ANS_DELETE_USER = "test@test.com is deleted";
    public static String ANS_CREATE_OFFER = "New offer created successfully";
    public static String ANS_DELETE_OFFER = "false";

    public static int NOT_LOGGED_IN = 0;
}
