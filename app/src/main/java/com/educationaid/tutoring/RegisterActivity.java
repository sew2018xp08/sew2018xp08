package com.educationaid.tutoring;

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
            }

        }
    }

    private Boolean registryValidation() {
        if(((TextView)findViewById(R.id.txtNameRegistry)).getText().toString().equals(""))
        {
            ((TextView)findViewById(R.id.lblErrorNameRegistry)).setText("Darf nicht leer sein!");
            return false;
        }

        if(((TextView)findViewById(R.id.txtSurnameRegistry)).getText().toString().equals("")){
            ((TextView)findViewById(R.id.lblErrorSurnameRegistry)).setText("Darf nicht leer sein!");
            return false;
        }

        if(((TextView)findViewById(R.id.txtEmailRegistry)).getText().toString().equals(""))
        {
            ((TextView)findViewById(R.id.lblErrorEmailRegistry)).setText("Darf nicht leer sein!");
            return false;
        }

        if(((TextView)findViewById(R.id.txtPasswordRegistry)).getText().toString().equals("")){
            ((TextView)findViewById(R.id.lblErrorPasswordRegistry)).setText("Darf nicht leer sein!");
            return false;
        }

        if(((TextView)findViewById(R.id.txtPasswordconfRegistry)).getText().toString().equals("")){
            ((TextView)findViewById(R.id.lblErrorPasswordconfRegistry)).setText("Darf nicht leer sein!");
            return false;
        }

        if(!(((TextView)findViewById(R.id.txtPasswordRegistry)).getText().toString().equals(((TextView)findViewById(R.id.txtPasswordconfRegistry)).getText().toString()))){
            ((TextView)findViewById(R.id.lblErrorPasswordconfRegistry)).setText("Passwörter stimmen nicht überein!");
            ((TextView)findViewById(R.id.lblErrorPasswordRegistry)).setText("Passwörter stimmen nicht überein!");
            return false;
        }
        return true;
    }
}
