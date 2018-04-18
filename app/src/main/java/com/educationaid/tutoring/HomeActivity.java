package com.educationaid.tutoring;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.educationaid.tutoring.Model.User;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    public static User currentUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        System.out.println(currentUser.getUserId());
        ((Button) findViewById(R.id.btnLogin)).setOnClickListener(this);

        if(currentUser.getUserId() != 0) {

        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnLogin:
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        }
    }
}
