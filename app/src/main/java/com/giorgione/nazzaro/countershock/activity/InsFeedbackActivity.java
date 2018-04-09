package com.giorgione.nazzaro.countershock.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.giorgione.nazzaro.countershock.R;
import com.giorgione.nazzaro.countershock.database.DBManager;

public class InsFeedbackActivity extends AppCompatActivity {

    DBManager mydb;
    private String email;
    private int idp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_insert_feedback);

        mydb = new DBManager(this);
        mydb=mydb.open();

        Bundle b = getIntent().getExtras();
        email=b.getString("email");
        idp=b.getInt("idp");

        final TextView txBody = (TextView) findViewById(R.id.editFeedback);
        final TextView txTitle = (TextView) findViewById(R.id.feedTitle);
        final RatingBar txVote = (RatingBar) findViewById(R.id.voteBar);

        final Button confirm = (Button) findViewById(R.id.submitFeedback);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=txTitle.getText().toString();
                String body=txBody.getText().toString();
                float vote = txVote.getRating();
                mydb.insertFeedback(email,idp,title,body,vote);

                Toast.makeText(getApplicationContext(),"commento inserito",Toast.LENGTH_LONG).show();
                Intent i=new Intent(InsFeedbackActivity.this,UserActivity.class);
                startActivity(i);
            }

            // mydb.insertFeedback(email,idp,title.getText().toString(),body.getText().toString(),voto);
        });
    }
}