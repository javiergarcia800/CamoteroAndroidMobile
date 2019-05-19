package com.mobile.camotero;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobile.camotero.permission.Permissions;
import com.mobile.camotero.util.LocationUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mMap;
    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);

        return v;
    }

    /*@Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }*/

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Get localitation


        // TODO Check if GPS is enabled or not.

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Log.i("CAMOTERO", "Latitud" + location.getLatitude() + ", Longitud:" + location.getLongitude());

                float zoom = 15.5f;

                // Add a marker in Sydney and move the camera
                LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());

                // Se guarda la localización actual.
                LocationUtil locationUtil = LocationUtil.getInstance();
                locationUtil.setLatLng(currentLocation);

                mMap.addMarker(new MarkerOptions().position(currentLocation).title("Mi ubicación"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationUtil.getLatLng(), zoom));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (!Permissions.Check_FINE_LOCATION(getActivity()) ) {
            Permissions.Request_FINE_LOCATION(getActivity(), 22);
        } else {
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
        }
    }

}
