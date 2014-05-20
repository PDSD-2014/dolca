package com.thermostat.service;

import com.thermostat.model.Place;
import com.thermostat.model.Token;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

import java.util.List;

public interface PlaceService {

    @GET("/places")
    void list(Callback<List<Place>> cb);

    @GET("/places")
    List<Place> list();
}