package com.thermostat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.thermostat.model.Place;
import com.thermostat.service.PlaceService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.List;

public class MainFragment extends Fragment {

    public static final String TAG = "MainFragment";
    protected static List<Place> places;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        refreshList();
        return view;
    }

    public void refreshList ()
    {
        RestAdapter client = ApiClient.getInstance();
        final PlaceService placeService = client.create(PlaceService.class);

        new Thread(new Runnable() {
            @Override
            public void run() {
                placeService.list(new Callback<List<Place>>() {
                    @Override
                    public void success(final List<Place> places, Response response) {

                        ListView listView = (ListView) getActivity().findViewById(R.id.placesList);
                        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, places);
                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener(new OnItemClickListener() {
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                ((MainActivity) getActivity()).onPlaceClick(places.get(position));
                            }
                        });
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getResponse().getStatus() + "");
                    }
                });
            }
        }).start();
    }
}
