package com.educationaid.tutoring;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.educationaid.tutoring.Utils.HttpUtilRegistry;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        (findViewById(R.id.btnRegistry)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {




        if(view.getId() == R.id.btnRegistry){
            if(registryValidation()) {
                HttpUtilRegistry.doRegister(((TextView)findViewById(R.id.txtNameRegistry)).getText().toString(), ((TextView)findViewById(R.id.txtSurnameRegistry)).getText().toString(),
                        ((TextView)findViewById(R.id.txtEmailRegistry)).getText().toString(), ((TextView)findViewById(R.id.txtPasswordRegistry)).getText().toString());
                startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
            }

        }
    }

    private Boolean registryValidation() {
        if(((TextView)findViewById(R.id.txtNameRegistry)).getText().toString().equals(""))
        {
            ((TextView)findViewById(R.id.lblErrorNameRegistry)).setVisibility(View.VISIBLE);
            return false;
        }
        else
        {
            ((TextView)findViewById(R.id.lblErrorNameRegistry)).setVisibility(View.INVISIBLE);
        }

        if(((TextView)findViewById(R.id.txtSurnameRegistry)).getText().toString().equals("")){
            ((TextView)findViewById(R.id.lblErrorSurnameRegistry)).setVisibility(View.VISIBLE);
            return false;
        }
        else
        {
            ((TextView)findViewById(R.id.lblErrorSurnameRegistry)).setVisibility(View.INVISIBLE);
        }

        if(((TextView)findViewById(R.id.txtEmailRegistry)).getText().toString().equals(""))
        {
            ((TextView)findViewById(R.id.lblErrorEmailRegistry)).setVisibility(View.VISIBLE);
            return false;
        }
        else
        {
            ((TextView)findViewById(R.id.lblErrorEmailRegistry)).setVisibility(View.INVISIBLE);
        }

        if(((TextView)findViewById(R.id.txtPasswordRegistry)).getText().toString().equals("")){
            ((TextView)findViewById(R.id.lblErrorPasswordRegistry)).setText("Darf nicht leer sein!");
            ((TextView)findViewById(R.id.lblErrorPasswordRegistry)).setVisibility(View.VISIBLE);
            return false;
        }
        else
        {
            ((TextView)findViewById(R.id.lblErrorPasswordRegistry)).setVisibility(View.INVISIBLE);
        }

        if(((TextView)findViewById(R.id.txtPasswordconfRegistry)).getText().toString().equals("")){
            ((TextView)findViewById(R.id.lblErrorPasswordconfRegistry)).setText("Darf nicht leer sein!");
            ((TextView)findViewById(R.id.lblErrorPasswordconfRegistry)).setVisibility(View.VISIBLE);
            return false;
        }
        else
        {
            ((TextView)findViewById(R.id.lblErrorPasswordconfRegistry)).setVisibility(View.INVISIBLE);
        }

        if(!(((TextView)findViewById(R.id.txtPasswordRegistry)).getText().toString().equals(((TextView)findViewById(R.id.txtPasswordconfRegistry)).getText().toString()))){
            ((TextView)findViewById(R.id.lblErrorPasswordRegistry)).setText("Passwörter stimmen nicht überein!");
            ((TextView)findViewById(R.id.lblErrorPasswordRegistry)).setVisibility(View.VISIBLE);
            ((TextView)findViewById(R.id.lblErrorPasswordconfRegistry)).setText("Passwörter stimmen nicht überein!");
            ((TextView)findViewById(R.id.lblErrorPasswordconfRegistry)).setVisibility(View.VISIBLE);
            return false;
        }
        else
        {
            ((TextView)findViewById(R.id.lblErrorPasswordRegistry)).setVisibility(View.INVISIBLE);
            ((TextView)findViewById(R.id.lblErrorPasswordconfRegistry)).setVisibility(View.INVISIBLE);
        }
        return true;
    }
}
