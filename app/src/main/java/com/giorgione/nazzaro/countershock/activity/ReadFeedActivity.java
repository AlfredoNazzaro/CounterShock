package com.giorgione.nazzaro.countershock.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.giorgione.nazzaro.countershock.R;
import com.giorgione.nazzaro.countershock.database.DBManager;
import com.giorgione.nazzaro.countershock.fragment.ReadFeedbackFragment;
import com.giorgione.nazzaro.countershock.share.Feedback;

import java.util.ArrayList;

public class ReadFeedActivity extends AppCompatActivity {

    private DBManager mydb;
    private int idp;
    private ListView lw;
    private ArrayList<Feedback> lf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_feed);


        mydb = new DBManager(this);
        mydb = mydb.open();

        Bundle b = getIntent().getExtras();
        idp = b.getInt("idp");

        lf = mydb.getFeedbackByRoad(idp);
        // Create the adapter to convert the array to views
        FeedbackAdapter adapter = new FeedbackAdapter(lf, this);
        // Attach the adapter to a ListView
        lw = (ListView) findViewById(R.id.FeedView);
        lw.setAdapter(adapter);

        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.contentFeedback);
        FragmentManager fragmentManager;
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final Feedback f = lf.get(position);

                rl.removeAllViewsInLayout();

                FragmentManager fragmentManager;
                fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                ReadFeedbackFragment f1 = new ReadFeedbackFragment();
                fragmentTransaction.add(R.id.contentFeedback, f1);
                fragmentTransaction.addToBackStack(null);//Col pulsante indietro torno indietro col fragment!!!
                fragmentTransaction.commit();
                Bundle b = new Bundle();
                b.putString("title", f.getTitle());
                b.putString("body", f.getBody());
                b.putFloat("vote", f.getVoto());
                f1.setArguments(b);
            }
        });
    }

    public void onBackPressed() {
        Intent i=new Intent(ReadFeedActivity.this,UserActivity.class);
        startActivity(i);
        super.onBackPressed();
    }
}
