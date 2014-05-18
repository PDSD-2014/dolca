package com.thermostat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.LoginButton;

import java.util.Arrays;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private static final Authentication auth = Authentication.getInstance();

    private UiLifecycleHelper uiHelper;

    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        uiHelper = new UiLifecycleHelper(getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);
    }

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            Session facebookSession = Session.getActiveSession();
            String facebookToken = session.getAccessToken();

            if (auth.loginWithFacebook(facebookToken)) {
                ((MainActivity) getActivity()).onLogin();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Create the view
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Se Facebook Login button properties
        LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
        authButton.setFragment(this);
        authButton.setReadPermissions(Arrays.asList("email"));

        // Set login button action
        Button loginButton = (Button) view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick (View view) {
        EditText emailField = (EditText) getView().findViewById(R.id.email_field);
        EditText passwordField = (EditText) getView().findViewById(R.id.password_field);

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        Authentication auth = Authentication.getInstance();
        boolean ok = auth.loginWithEmail(email, password);

        if (ok) {
            ((MainActivity) getActivity()).onLogin();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        // For scenarios where the main activity is launched and user
        // session is not null, the session state change notification
        // may not be triggered. Trigger it if it's open/closed.
        Session session = Session.getActiveSession();
        if (session != null &&
                (session.isOpened() || session.isClosed()) ) {
            onSessionStateChange(session, session.getState(), null);
        }

        uiHelper.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }
}
