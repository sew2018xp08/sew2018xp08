package com.educationaid.tutoring;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateOfferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_offer);

        final Button addButton = (Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v){
                final TextInputLayout editTitle = (TextInputLayout) findViewById(R.id.title_text);
                final TextInputLayout descriptionTitle = (TextInputLayout) findViewById(R.id.description_text);

                if(editTitle.getEditText().getText().toString().equals("")){
                    editTitle.setError("No Title");
                }
                if(descriptionTitle.getEditText().getText().toString().equals("")){
                    descriptionTitle.setError("No Description");
                }
                if((!editTitle.getEditText().getText().toString().equals(""))
                        && (descriptionTitle.getEditText().getText().toString().equals(""))){
                    // send database request
                    startActivity(new Intent(CreateOfferActivity.this, HomeActivity.class));
                }
            }
        });
        final Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateOfferActivity.this, HomeActivity.class));

            }
        });

    }
}
