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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        (findViewById(R.id.btnLogin)).setOnClickListener(this);
        (findViewById(R.id.btnRegister)).setOnClickListener(this);
        (findViewById(R.id.txtWarning)).setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;
        switch (clickedButton.getId()) {
            case R.id.btnLogin:
                if((((EditText)findViewById(R.id.txtUserName)).getText().toString()).equals("") || (((EditText) findViewById(R.id.txtPassword)).getText().toString()).equals(""))
                {
                    //todo: Create string class..
                    (findViewById(R.id.txtWarning)).setVisibility(View.VISIBLE);
                }
                //do something
                break;
            case R.id.btnRegister:
                startActivity(new Intent(LoginActivity.this, RegistryActivity.class));
                break;
        }
    }
}
