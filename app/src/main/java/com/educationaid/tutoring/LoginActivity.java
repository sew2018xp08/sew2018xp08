package com.educationaid.tutoring;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.educationaid.tutoring.Constants.Constants;
import com.educationaid.tutoring.WebService.WebService;

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
                (findViewById(R.id.txtWarning)).setVisibility(View.INVISIBLE);
                if((((EditText)findViewById(R.id.txtUserName)).getText().toString()).equals("") || (((EditText) findViewById(R.id.txtPassword)).getText().toString()).equals("")
                        || (!(((EditText)findViewById(R.id.txtUserName)).getText().toString()).contains("@")))
                {
                    (findViewById(R.id.txtWarning)).setVisibility(View.VISIBLE);
                    break;
                }

                doLogin(((EditText)findViewById(R.id.txtUserName)).getText().toString(), ((EditText)findViewById(R.id.txtPassword)).getText().toString());

                break;
            case R.id.btnRegister:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    public static void doLogin(final String givenEmail, final String givenPassword) {
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                return WebService.Login(params[0], params[1]);
            }
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                if(result.equals(Constants.ANS_RIGHT_USERNAME_PASSWORD)){
                    System.out.println("HTTP POST is working...");
                }else{
                    System.out.println("Invalid POST req...");
                }
            }
        }.execute(givenEmail, givenPassword);
    }
}
