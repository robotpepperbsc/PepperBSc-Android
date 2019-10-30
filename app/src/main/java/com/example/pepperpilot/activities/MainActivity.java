package com.example.pepperpilot.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.pepperpilot.R;
import com.example.pepperpilot.ScenariosSingleton;
import com.example.pepperpilot.fragments.DisplayOnScreenFragment;
import com.example.pepperpilot.fragments.IpConnectionFragment;
import com.example.pepperpilot.fragments.MovementFragment;
import com.example.pepperpilot.fragments.RecordingsFragment;
import com.example.pepperpilot.fragments.ScenariosFragment;
import com.example.pepperpilot.fragments.SettingsFragment;
import com.example.pepperpilot.fragments.SpeechFragmeent;
import com.example.pepperpilot.models.Scenario;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment, new IpConnectionFragment());
        fragmentTransaction.commit();


        //Load scenarios from shared prefs
        ScenariosSingleton.getInstance().getScenarios().clear();
        SharedPreferences mPrefs = getPreferences(Context.MODE_PRIVATE);
        Gson gson = new Gson();
        int size = mPrefs.getInt("scenarioSize", 0);
        for (int i = 0; i < size; i++) {
            String json = mPrefs.getString("scenario" + i, "");
            Scenario scenario = gson.fromJson(json, Scenario.class);
            ScenariosSingleton.getInstance().getScenarios().add(scenario);
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

        if (id == R.id.nav_connection) {
            setTitle("Połączenie z serwerem");
            IpConnectionFragment fragment = new IpConnectionFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit();
        } else if (id == R.id.nav_speech) {
            setTitle("Mowa robota");
            SpeechFragmeent fragment = new SpeechFragmeent();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit();
        } else if (id == R.id.nav_movement) {
            setTitle("Ruch robota");
            MovementFragment fragment = new MovementFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit();
        } else if (id == R.id.nav_display_on_screen) {
            setTitle("Wyświetl na tablecie");
            DisplayOnScreenFragment fragment = new DisplayOnScreenFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit();
        } else if (id == R.id.nav_settings) {
            setTitle("Settings");
            SettingsFragment fragment = new SettingsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit();
        } else if (id == R.id.nav_recordings) {
            setTitle("Recordings");
            RecordingsFragment fragment = new RecordingsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit();
        } else if (id == R.id.nav_scenarios) {
            setTitle("Scenarios");
            ScenariosFragment fragment = new ScenariosFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit();
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();

        //Save scenarios
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        int cnt = 0;

        for (Scenario scenario : ScenariosSingleton.getInstance().getScenarios()) {
            String json = gson.toJson(scenario);
            prefsEditor.putString("scenario" + cnt, json);
            prefsEditor.apply();
            cnt++;
        }

        prefsEditor.putInt("scenarioSize", cnt);
        prefsEditor.apply();


    }
}
