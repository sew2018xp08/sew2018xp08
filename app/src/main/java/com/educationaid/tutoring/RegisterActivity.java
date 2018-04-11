package com.educationaid.tutoring;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private Button registry_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
         (findViewById(R.id.btnRegistry)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnRegistry)
            registryValidation();
    }

    private void registryValidation() {
        if(((TextView)findViewById(R.id.txtNameRegistry)).getText().equals(""))
            ((TextView)findViewById(R.id.lblErrorNameRegistry)).setText("Darf nicht leer sein!");

        if(((TextView)findViewById(R.id.txtSurnameRegistry)).getText().equals(""))
            ((TextView)findViewById(R.id.lblErrorSurnameRegistry)).setText("Darf nicht leer sein!");

        if(((TextView)findViewById(R.id.txtEmailRegistry)).getText().equals(""))
            ((TextView)findViewById(R.id.lblErrorEmailRegistry)).setText("Darf nicht leer sein!");

        if(((TextView)findViewById(R.id.txtPasswordRegistry)).getText().equals(""))
            ((TextView)findViewById(R.id.lblErrorPasswordRegistry)).setText("Darf nicht leer sein!");

        if(((TextView)findViewById(R.id.txtPasswordconfRegistry)).getText().equals(""))
            ((TextView)findViewById(R.id.lblErrorPasswordconfRegistry)).setText("Darf nicht leer sein!");

        if(!(((TextView)findViewById(R.id.txtPasswordRegistry)).getText().equals(((TextView)findViewById(R.id.txtPasswordconfRegistry)).getText()))){
            ((TextView)findViewById(R.id.lblErrorPasswordconfRegistry)).setText("Passwörter stimmen nicht überein!");
            ((TextView)findViewById(R.id.lblErrorPasswordRegistry)).setText("Passwörter stimmen nicht überein!");
        }
    }
}
