package com.giorgione.nazzaro.countershock.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.giorgione.nazzaro.countershock.share.Percorso;
import com.giorgione.nazzaro.countershock.R;
import com.giorgione.nazzaro.countershock.database.DBManager;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    private Boolean exit = false;
    private DBManager mydb;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mydb = new DBManager(this);
        mydb = mydb.open();
        sp=getSharedPreferences("login",MODE_PRIVATE);

        final ArrayList<Percorso> lp = mydb.getAllRoad();
        // Create the adapter to convert the array to views
        RoadAdapter adapter = new RoadAdapter(lp, this);
        // Attach the adapter to a ListView
        ListView lw = (ListView) findViewById(R.id.RoadView);
        lw.setAdapter(adapter);

        final RelativeLayout rl= (RelativeLayout) findViewById(R.id.contentUser);

        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final Dialog builder = new Dialog(UserActivity.this);
                // Get the layout inflater
                LayoutInflater inflater = UserActivity.this.getLayoutInflater();

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setContentView(inflater.inflate(R.layout.dialog, null));
                builder.setTitle("Dettagli percorso");

                final Percorso p = lp.get(position);
                final TextView id_p = (TextView) builder.findViewById(R.id.did);
                id_p.setText(" "+Integer.toString(p.getId()));
                final TextView partenza = (TextView) builder.findViewById(R.id.dpart);
                partenza.setText(" "+p.getPartenza());
                final TextView destinazione = (TextView) builder.findViewById(R.id.ddest);
                destinazione.setText(" "+p.getDestinazione());
                final TextView num_fossi = (TextView) builder.findViewById(R.id.dnumfossi);
                num_fossi.setText(" "+Integer.toString(p.getNumero_fossi()));
                final TextView km = (TextView) builder.findViewById(R.id.dkm);
                km.setText(" "+Double.toString(p.getKm()));

                final Button feedback = (Button) builder.findViewById(R.id.buttonFeedback);
                feedback.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        Intent i = new Intent(UserActivity.this, InsFeedbackActivity.class);
                        Bundle b = new Bundle();
                        b.putInt("idp", p.getId()); //Your id
                        b.putString("email",sp.getString("user"," "));
                        i.putExtras(b);
                        builder.dismiss();
                        startActivity(i);
                    }
                });

                final Button readFeedback = (Button) builder.findViewById(R.id.buttonReadFeedback);
                readFeedback.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        Intent i = new Intent(UserActivity.this, ReadFeedActivity.class);
                        Bundle b = new Bundle();
                        b.putInt("idp", p.getId()); //Your id
                        i.putExtras(b);
                        builder.dismiss();
                        startActivity(i);
                    }
                });
                builder.show();
            }
        });

        final Button insertRoad = (Button) findViewById(R.id.insRoad);
        insertRoad.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(UserActivity.this, InsertRoadActivity.class);
                startActivity(i);
            }
        });

        final Button logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                sp = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor e = sp.edit();
                e.clear();
                e.commit();
                Intent i = new Intent(UserActivity.this, MainActivity.class);
                Toast.makeText(getApplicationContext(), "logout riuscito", Toast.LENGTH_LONG).show();
                startActivity(i);
                finish();
            }
        });
    }

    public void onBackPressed() {
        setContentView(R.layout.activity_user);
        super.onBackPressed();
    }
}
