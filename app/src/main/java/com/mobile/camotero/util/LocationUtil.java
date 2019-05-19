package com.mobile.camotero.util;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class LocationUtil {

    private static LocationUtil locationUtil = new LocationUtil();

    private LatLng latLngCurrent;

    private LocationUtil() {
        super();
    }

    public static LocationUtil getInstance() {
        return locationUtil;
    }

    public void setLatLng(LatLng latLng) {
        this.latLngCurrent = latLng;
    }

    public LatLng getLatLng() {
        return this.latLngCurrent;
    }

}
