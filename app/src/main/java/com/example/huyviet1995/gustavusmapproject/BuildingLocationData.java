package com.example.huyviet1995.gustavusmapproject;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

/**
 * Created by huyviet1995 on 6/25/16.
 */
public class BuildingLocationData {
    public enum Place {
        CAMPUSCENTER, LUNDCENTER, LIBRARY;
    }

    Place mPlace;
    private PolygonOptions polygonPositions;
    private MarkerOptions buildingLocation;

    public BuildingLocationData(Place place) {
        this.mPlace = place;
    }

    public MarkerOptions buildingLocation() {
        switch (mPlace) {
            case CAMPUSCENTER:
                buildingLocation = new MarkerOptions()
                        .position(new LatLng(44.324272, -93.970848))
                        .title("Campus Center")
                        .snippet("The center building of Gustavus");
                break;
            case LUNDCENTER:
                buildingLocation = new MarkerOptions()
                        .position(new LatLng(44.325231, -93.971913))
                        .title("Lund Center")
                        .snippet("The place to work out");
                break;
        }
        return buildingLocation;
    }

    public PolygonOptions locationDetails() {
        switch (mPlace) {
            case CAMPUSCENTER:
                polygonPositions = new PolygonOptions()
                        .add(new LatLng(44.324745, -93.970942))
                        .add(new LatLng(44.324505, -93.971138))
                        .add(new LatLng(44.324547, -93.971275))
                        .add(new LatLng(44.324457, -93.971331))
                        .add(new LatLng(44.324296, -93.971063))
                        .add(new LatLng(44.324171, -93.971154))
                        .add(new LatLng(44.324117, -93.971054))
                        .add(new LatLng(44.323868, -93.971247))
                        .add(new LatLng(44.323711, -93.970861))
                        .add(new LatLng(44.324454, -93.970335))
                        .add(new LatLng(44.324745, -93.970942))
                        .clickable(true)
                        .visible(false);
                break;
            default:
                break;
        }
        return polygonPositions;
    }
}
