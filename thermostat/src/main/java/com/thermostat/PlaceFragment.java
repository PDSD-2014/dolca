package com.thermostat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.thermostat.model.Place;

public class PlaceFragment extends Fragment {

    private Place place;

    public PlaceFragment (Place place) {
        this.place = place;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place, container, false);
        ((TextView) view.findViewById(R.id.placeName)).setText(place.getName());
        ((TextView) view.findViewById(R.id.temperature)).setText(10 + "° C");
        return view;
    }
}
