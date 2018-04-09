package com.giorgione.nazzaro.countershock.activity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.giorgione.nazzaro.countershock.R;
import com.giorgione.nazzaro.countershock.database.DBManager;


public class InsertRoadActivity extends AppCompatActivity implements SensorEventListener {

    DBManager mydb;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private float lastAcc = 0.0f;
    private float acceleration = 0.0f;
    private float totAcc = 0.0f;
    private boolean onEvent = false;
    private int num_fossi=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_road);

        mydb=new DBManager(this);
        mydb=mydb.open();
        Button buttonSave= (Button) findViewById(R.id.buttonSave);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lastAcc=SensorManager.GRAVITY_EARTH;
        acceleration=SensorManager.GRAVITY_EARTH;
        mSensorManager.unregisterListener(this);

        final EditText EditPartenza = (EditText) findViewById(R.id.editPartenza);
        final EditText EditDestinazione = (EditText) findViewById(R.id.editArrivo);
        final EditText EditNumFossi = (EditText) findViewById(R.id.editNumFossi);
        final EditText EditKm = (EditText) findViewById(R.id.editKm);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String partenza= EditPartenza.getText().toString();
                String destinazione= EditDestinazione.getText().toString();
                int fossi= Integer.parseInt(EditNumFossi.getText().toString());
                double km= Double.parseDouble(EditKm.getText().toString());
                mydb.insertRoad(partenza,destinazione,fossi,km);
                Toast.makeText(getApplicationContext(),"percorso inserito",Toast.LENGTH_LONG).show();
                Intent i=new Intent(InsertRoadActivity.this,UserActivity.class);
                startActivity(i);
            }
        });

        final Button buttonFossi= (Button) findViewById(R.id.buttonFossi);

        buttonFossi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonFossi.getText().toString().equals("inizia")) {
                    buttonFossi.setText("termina");
                    start();
                }
                else{
                    buttonFossi.setText("inizia");
                    stop();
                }
            }
        });
    }

    public void start(){
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stop(){
        mSensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (!onEvent) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            TextView fossi=(TextView) findViewById(R.id.editNumFossi);

            lastAcc = acceleration;
            acceleration = x*x+y*y+z*z;
            float diff = acceleration - lastAcc;
            totAcc = diff*acceleration;
            if (totAcc>50000) {
                onEvent=true;
                num_fossi=num_fossi+1;
                fossi.setText(""+num_fossi);
                onEvent=false;
            }
        }
    }
    public void cleanPartenza(View v) {
        TextView part=(TextView) findViewById(R.id.editPartenza);
        part.setText("");
    }

    public void cleanDestinazione(View v) {
        TextView dest=(TextView) findViewById(R.id.editArrivo);
        dest.setText("");
    }
    public void cleanKm(View v) {
        TextView km=(TextView) findViewById(R.id.editKm);
        km.setText("");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
