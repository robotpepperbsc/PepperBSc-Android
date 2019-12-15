package com.example.pepperpilot.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pepperpilot.R;
import com.example.pepperpilot.fragments.BehaviorsFragment;
import com.example.pepperpilot.fragments.DisplayOnScreenFragment;
import com.example.pepperpilot.fragments.IpConnectionFragment;
import com.example.pepperpilot.fragments.MovementFragment;
import com.example.pepperpilot.fragments.RecordingsFragment;
import com.example.pepperpilot.fragments.ScenariosFragment;
import com.example.pepperpilot.fragments.SettingsFragment;
import com.example.pepperpilot.fragments.SpeechFragment;
import com.example.pepperpilot.interfaces.StringCallback;
import com.example.pepperpilot.models.Scenario;
import com.example.pepperpilot.requests.RequestMaker;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button stopB;
    private TextView toolbarTitleTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        stopB = findViewById(R.id.stop_button);
        toolbarTitleTV = findViewById(R.id.toolbar_title_text_view);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        toolbarTitleTV.setText("Połączenie z serwerem");
        fragmentTransaction.replace(R.id.fragment, new IpConnectionFragment());
        fragmentTransaction.commit();


        stopB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestMaker.clearQueue(new StringCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(MainActivity.this,"STOP",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String result) {

                    }
                },MainActivity.this);

            }
        });


        Scenario scenario = new Scenario("NAzwa","opis","2019-10-10");

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_connection) {
            toolbarTitleTV.setText("Połączenie z serwerem");
            fragment = new IpConnectionFragment();
        } else if (id == R.id.nav_speech) {
            toolbarTitleTV.setText("Mowa robota");
            fragment = new SpeechFragment();
        } else if (id == R.id.nav_movement) {
            toolbarTitleTV.setText("Ruch robota");
            fragment = new MovementFragment();
        } else if (id == R.id.nav_behaviors) {
            toolbarTitleTV.setText("Zachowania");
            fragment = new BehaviorsFragment();
        } else if (id == R.id.nav_display_on_screen) {
            toolbarTitleTV.setText("Wyświetl na tablecie");
            fragment = new DisplayOnScreenFragment();
        } else if (id == R.id.nav_settings) {
            toolbarTitleTV.setText("Ustawienia");
            fragment = new SettingsFragment();
        } else if (id == R.id.nav_recordings) {
            toolbarTitleTV.setText("Nagrania");
            fragment = new RecordingsFragment();
        } else if (id == R.id.nav_scenarios) {
            toolbarTitleTV.setText("Scenariusze");
            fragment = new ScenariosFragment();
        }

        fragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();


    }
}
