package com.educationaid.tutoring;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txt_username;
    EditText txt_password;
    TextView txt_warning;
    Button btn_login;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        txt_username = (EditText) findViewById(R.id.txtUserName);
        txt_password = (EditText) findViewById(R.id.txtPassword);
        txt_warning = (TextView) findViewById(R.id.txtWarning);
        btn_login = (Button) findViewById(R.id.btnLogin);
        btn_login.setOnClickListener(this);
        btn_register = (Button) findViewById(R.id.btnRegister);
        btn_register.setOnClickListener(this);
        txt_warning.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;
        switch (clickedButton.getId()) {
            case R.id.btnLogin:
                if(txt_username.getText().equals("") || txt_password.getText().equals(""))
                {
                    //todo: Create string class..
                    txt_warning.setVisibility(View.VISIBLE);
                }
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
