package com.example.huyviet1995.gustavusmapproject;

import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by huyviet1995 on 6/27/16.
 */
public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    public CustomInfoWindowAdapter() {

    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
