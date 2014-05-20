package com.thermostat.service;

import com.thermostat.model.Token;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface AuthorizationService {
    @POST("/authorizations")
    void authorize(@Body String email, @Body String password, Callback<Token> cb);

    @POST("/authorizations")
    Token authorize(@Body String email, @Body String password);

    @POST("/authorizations")
    void authorize(@Body String facebookToken, Callback<Token> cb);

    @POST("/authorizations")
    Token authorize(@Body String facebookToken);
}
