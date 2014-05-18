package com.thermostat;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.crashlytics.android.Crashlytics;

public class MainActivity extends ActionBarActivity {

    private LoginFragment loginFragment;
    private MainFragment mainFragment;
    private static final Authentication auth = Authentication.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Crashlytics.start(this);

        setContentView(R.layout.activity_main);

        // Recover fragment instances
        if (savedInstanceState != null) {

            // Or set the fragment from restored state info
            loginFragment = (LoginFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.login_fragment);

            mainFragment = (MainFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.main_fragment);
        }

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

        synchronized (this) {
            if (mainFragment == null) {
                mainFragment = new MainFragment();
            }
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity, mainFragment)
                .commit();
    }

    protected void onLogout() {

        synchronized (this) {
            if (loginFragment == null) {
                loginFragment = new LoginFragment();
            }
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity, loginFragment)
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
