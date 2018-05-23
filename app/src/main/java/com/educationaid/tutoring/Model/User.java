package com.educationaid.tutoring.Model;

import android.graphics.Bitmap;

import com.educationaid.tutoring.Constants.Constants;

public class User {
    int id_;
    int admin_;
    String first_name_;
    String last_name_;
    boolean pro_user_;
    String email_;
    Bitmap profile_picture_;


    public User() {
        id_ = Constants.NOT_LOGGED_IN;
    }

    public User(int id, String first_name, String last_name, boolean pro_user, String email, int admin, Bitmap profile_picture) {
        id_ = id;
        first_name_ = first_name;
        last_name_ = last_name;
        pro_user_ = pro_user;
        email_ = email;
        admin_ = admin;
        profile_picture_ = profile_picture;
    }

    public int getUserId() {
        return id_;
    }

    public String getLastName() {
        return last_name_;
    }

    public String getFirstName() {
        return first_name_;
    }

    public boolean isProUser() {
        return pro_user_;
    }

    public String getEmail() {
        return email_;
    }

    public int getAdmin() {
        return admin_;
    }

    public Bitmap getProfilePicture() { return profile_picture_; }

    public void setProfilePicture(Bitmap map) { profile_picture_ = map; }

}
