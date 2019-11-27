package com.hungrydroid.restaurantapp.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hungrydroid.restaurantapp.R;


public class LocationFragment extends Fragment implements OnMapReadyCallback {

    public LocationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setScrollGesturesEnabled(true);
        //23.813638, 90.424189
        // Add a marker in Sydney and move the camera
        LatLng point = new LatLng(23.796166, 90.400856);
        googleMap.addMarker(new
                MarkerOptions().position(point).title("Restaurant"));
        point = new LatLng(23.813638, 90.424189);
        googleMap.addMarker(new
                MarkerOptions().position(point).title("Restaurant"));
        point = new LatLng(23.781041, 90.416420);
        googleMap.addMarker(new
                MarkerOptions().position(point).title("Restaurant"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(23.818332, 90.409925)));

        CameraPosition Me =
                new CameraPosition.Builder().target(new LatLng(23.818332, 90.409925))
                        .zoom(12.5f)
                        .build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Me));

    }


}
