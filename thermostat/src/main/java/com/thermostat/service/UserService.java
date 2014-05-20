package com.thermostat.service;

import com.thermostat.model.User;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface UserService {
    @GET("/users/{email}")
    User listRepos(@Path("user") String email);

    @GET("/users/{email}")
    void listRepos(@Path("user") String email, Callback<User> cb);
}
