package com.thermostat;

import com.facebook.Session;

public class Authentication {

    private static Authentication instance;
    private Authentication () {}

    private static class LazyHolder {
        private static final Authentication INSTANCE = new Authentication();
    }

    public static Authentication getInstance () {
        return LazyHolder.INSTANCE;
    }

    /**
     * The user used Facebook Login and we have to check if
     * it has a valid account and get a token from our backend
     *
     * @param token
     * @return if the login was successful or not
     */
    public boolean loginWithFacebook (String token) {
        // TODO Ask the server if the token is valid
        return true;
    }

    /**
     * The user used the authentication form in order to log in
     *
     * @param email
     * @param password
     * @return if the login was successful or not
     */
    public boolean loginWithEmail (String email, String password) {
        // TODO Ask the server if the credentials are valid
        return true;
    }

    /**
     * Check if we have a valid token
     * @return if we have a valid token or not
     */
    public boolean loggedIn () {

        // Check if the user has an active token
        if (false) {

            // The user is authenticated and it has a valid token
            // TODO check if we have a token and if we do check if it is valid
            return true;

        } else {

            // If not check if the user is logged in with Facebook
            Session session = Session.getActiveSession();

            // If there is a valid Facebook session try to login in order to get a new token
            if (session != null && session.isOpened()) {

                // Try to authenticate with facebook
                boolean facebookLogin = loginWithFacebook(session.getAccessToken());

                // If the server does not recognize the user we should destroy the fb session
                // We call logout because it destroy the fb token and it shows the login screen
                if (!facebookLogin) {
                    logOut();
                    return false;
                }

                return true;
            }
        }

        return false;
    }

    public void logOut () {

        // Destroy out token if we have one
        // TODO Add co to destroy the token

        // Destroy facebook session if there is one
        Session session = Session.getActiveSession();
        if (session != null) {
            session.closeAndClearTokenInformation();
        }
    }
}
