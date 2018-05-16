package com.educationaid.tutoring;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.educationaid.tutoring.Constants.Constants;
import com.educationaid.tutoring.WebService.WebService;

public class RegistryActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        (findViewById(R.id.btnRegistry)).setOnClickListener(this);
        ImageView btnLicenceInfo = (ImageView) findViewById(R.id.btnLicenceInfo);
        btnLicenceInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LicenceInfoActivity.class));
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnRegistry){
            if(registryValidation()) {
                RadioGroup radioGroup = findViewById(R.id.rgProUser);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                String radioButton = ((RadioButton) findViewById(selectedId)).getText().toString();
                String isPro = radioButton.equals("Free") ? "0" : "1";

                doRegister(((TextView)findViewById(R.id.txtNameRegistry)).getText().toString(), ((TextView)findViewById(R.id.txtSurnameRegistry)).getText().toString(),
                        ((TextView) findViewById(R.id.txtEmailRegistry)).getText().toString(), ((TextView) findViewById(R.id.txtPasswordRegistry)).getText().toString(), isPro);
                } else
                Toast.makeText(RegistryActivity.this, "Check input",
                        Toast.LENGTH_LONG).show();
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

    public void doRegister(String givenFirstname, String givenLastname, String givenEmail, String givenPassword, String givenIsPro) {
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                return new WebService().Register(params[0], params[1], params[2], params[3], params[4]);
            }
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                if(result.equals(Constants.ANS_CREATED_USER_SUCCESFULLY)){
                    startActivity(new Intent(RegistryActivity.this, HomeActivity.class));
                    Toast.makeText(getApplicationContext(), R.string.successful_registry, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), R.string.user_alredy_exists, Toast.LENGTH_LONG).show();
                }
            }
        }.execute(givenFirstname, givenLastname, givenEmail, givenPassword, givenIsPro);
    }
}
