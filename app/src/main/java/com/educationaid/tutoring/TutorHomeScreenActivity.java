package com.educationaid.tutoring;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

import com.educationaid.tutoring.adapters.OfferListAdapter;

public class TutorHomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tutor_home_screen);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ListView listView = findViewById(R.id.offerListView);

        // specify an adapter (see also next example)
        String[] myDataset = {"Analysis T1", "Betriebsysteme"};
        OfferListAdapter oladapter = new OfferListAdapter(this, R.layout.textview_offer_item, myDataset);
        listView.setAdapter(oladapter);


        Button btnLogout = findViewById(R.id.buttonLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TutorHomeScreenActivity.this, HomeActivity.class);
                TutorHomeScreenActivity.this.startActivity(myIntent);
            }
        });

        Button btnAdd = findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TutorHomeScreenActivity.this, CreateOfferActivity.class);
                TutorHomeScreenActivity.this.startActivity(myIntent);
            }
        });
    }
}
