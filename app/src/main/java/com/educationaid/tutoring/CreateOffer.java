package com.educationaid.tutoring;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateOffer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_offer);

        final Button addButton = (Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v){
                final EditText editTitle = (EditText) findViewById(R.id.title_textbox);
                final EditText descriptionTitle = (EditText) findViewById(R.id.description_textbox);

                if(editTitle.getText().toString().equals("")){
                    editTitle.setError("No Title");
                }
                if(descriptionTitle.getText().toString().equals("")){
                    descriptionTitle.setError("No Description");
                }
            }
        });
    }
}
