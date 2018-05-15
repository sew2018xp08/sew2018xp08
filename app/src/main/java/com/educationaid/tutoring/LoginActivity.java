package com.educationaid.tutoring;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.educationaid.tutoring.Constants.Constants;
import com.educationaid.tutoring.Model.User;
import com.educationaid.tutoring.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;

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

    public void doLogin(String givenEmail, String givenPassword) {

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                return new WebService().Login(params[0], params[1]);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                if (!result.equals(Constants.ANS_RIGHT_USERNAME_PASSWORD)) {
                    try {
                        JSONArray obj = new JSONArray(result);
                        System.out.println("Hei sweetty <3");

                        HomeActivity.currentUser = new User(Integer.valueOf(obj.getJSONObject(0).getString("u_id")), obj.getJSONObject(0).getString("first_name"),
                                obj.getJSONObject(0).getString("last_name"), false, obj.getJSONObject(0).getString("email"),
                                Integer.valueOf(obj.getJSONObject(0).getString("admin")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    startActivity(new Intent(LoginActivity.this, TutorHomeScreenActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed!",
                            Toast.LENGTH_LONG).show();
                }
            }
        }.execute(givenEmail, givenPassword);
    }
}
