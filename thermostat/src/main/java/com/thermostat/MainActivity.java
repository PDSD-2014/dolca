package com.thermostat;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.crashlytics.android.Crashlytics;
import com.thermostat.model.Place;

public class MainActivity extends ActionBarActivity {

    private static final Authentication auth = Authentication.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Crashlytics.start(this);

        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

            // Check if the user has a valid token
            if (!auth.loggedIn()) {
                onLogout();
            } else {
                onLogin();
            }
        }
    }

    protected void onLogin () {

        MainFragment mainFragment = new MainFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity, mainFragment)
                .commit();
    }

    protected void onLogout() {

        LoginFragment loginFragment = new LoginFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity, loginFragment)
                .commit();
    }

    public void onPlaceClick(Place place) {

        PlaceFragment placeFragment = new PlaceFragment(place);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity, placeFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_logout) {
            auth.logOut();
            onLogout();
        }

        return super.onOptionsItemSelected(item);
    }
}
