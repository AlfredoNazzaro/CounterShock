package com.giorgione.nazzaro.countershock.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.giorgione.nazzaro.countershock.fragment.LoginFragment;
import com.giorgione.nazzaro.countershock.R;
import com.giorgione.nazzaro.countershock.fragment.RegistrationFragment;
import com.giorgione.nazzaro.countershock.fragment.ListRoadFragment;
import com.giorgione.nazzaro.countershock.database.DbHelper;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DbHelper database;
    SharedPreferences sp;
    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp=getSharedPreferences("login",MODE_PRIVATE);

        if(sp.contains("user") && sp.contains("password")){
            startActivity(new Intent(MainActivity.this,UserActivity.class));
            finish();   //finish current activity
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.setDrawerListener(toggle);
        }
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                restoreLayout();
                super.onBackPressed();
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }

        FragmentManager fragmentManager;
        fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

        RelativeLayout rl= (RelativeLayout) findViewById(R.id.fragment_container);
        switch (item.getItemId()) {
            case R.id.login:
                rl.removeAllViewsInLayout();
                LoginFragment f1 = new LoginFragment();
                fragmentTransaction.add(R.id.fragment_container, f1);
                fragmentTransaction.addToBackStack(null);//Col pulsante indietro torno indietro col fragment!!!
                fragmentTransaction.commit();
                return true;
            case R.id.reg:
                rl.removeAllViewsInLayout();
                RegistrationFragment f2 = new RegistrationFragment();
                fragmentTransaction.add(R.id.fragment_container,f2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            case R.id.visualization:
               /* Intent intent = new Intent(this, InsFeedbackActivity.class);
                startActivity(intent);*/
                rl.removeAllViewsInLayout();
                ListRoadFragment f3 = new ListRoadFragment();
                fragmentTransaction.add(R.id.fragment_container, f3);
                fragmentTransaction.addToBackStack(null);//Col pulsante indietro torno indietro col fragment!!!
                fragmentTransaction.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void restoreLayout(){

        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.setDrawerListener(toggle);
        }
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }
}