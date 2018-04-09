package com.educationaid.tutoring;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txt_username;
    TextView txt_password;
    Button btn_login;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        txt_username = (TextView) findViewById(R.id.txtUserName);
        txt_password = (TextView) findViewById(R.id.txtPassword);
        btn_login = (Button) findViewById(R.id.btnLogin);
        btn_login.setOnClickListener(this);
        btn_register = (Button) findViewById(R.id.btnRegister);
        btn_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;
        switch (clickedButton.getId()) {
            case R.id.btnLogin:
                //do something
                break;
            case R.id.btnRegister:
                //switch to RegisterActivity
                //Intent to_register_activity = new Intent(LoginActivity.this, RegisterActivity.class);
                //startActivity(to_register_activity);
                break;
        }
    }
}
