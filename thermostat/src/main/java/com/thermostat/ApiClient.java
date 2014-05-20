package com.thermostat;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class ApiClient {

    private ApiClient () {}

    public static RestAdapter getInstance () {
        RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint("http://ec2-54-228-216-195.eu-west-1.compute.amazonaws.com/")
            .build();
        return restAdapter;
    }
}
