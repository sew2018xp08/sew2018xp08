package com.educationaid.tutoring.Model;

import com.educationaid.tutoring.Constants.Constants;

public class User {
    int id_;
    int admin_;
    String first_name_;
    String last_name_;
    String email_;


    public User() {
        id_ = Constants.NOT_LOGGED_IN;
    }

    public User(int id, String first_name, String last_name, String email, int admin) {
        id_ = id;
        first_name_ = first_name;
        last_name_ = last_name;
        email_ = email;
        admin_ = admin;
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

    public String getEmail() {
        return email_;
    }

    public int getAdmin() {
        return admin_;
    }
}
