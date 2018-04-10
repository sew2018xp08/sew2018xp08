package com.educationaid.tutoring;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent to_login = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(to_login);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            
        }
    }
}
