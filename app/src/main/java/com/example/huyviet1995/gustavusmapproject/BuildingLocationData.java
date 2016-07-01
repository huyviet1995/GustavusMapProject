package com.example.huyviet1995.gustavusmapproject;

import android.content.Context;
import android.graphics.Color;
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
        CAMPUSCENTER, LUNDCENTER, LIBRARY, CAMPUSCENTERROOF,CAMPUSCENTERWALLS;
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
                        .strokeWidth(2)
                        .fillColor(Color.YELLOW)
                        .strokeColor(Color.BLUE)
                        .clickable(true)
                        .visible(true);
                break;
            case CAMPUSCENTERROOF:
                polygonPositions = new PolygonOptions()
                        .add(new LatLng(44.324865, -93.971012))
                        .add(new LatLng(44.324625, -93.971208))
                        .add(new LatLng(44.324667, -93.971345))
                        .add(new LatLng(44.324577, -93.971401))
                        .add(new LatLng(44.324416, -93.971133))
                        .add(new LatLng(44.324291, -93.971224))
                        .add(new LatLng(44.324237, -93.971124))
                        .add(new LatLng(44.323988, -93.971317))
                        .add(new LatLng(44.323831, -93.970931))
                        .add(new LatLng(44.324574, -93.970405))
                        .add(new LatLng(44.324865, -93.971012))
                        .fillColor(Color.parseColor("#B33333cc"))
                        .strokeWidth(2)
                        .strokeColor(Color.BLUE)
                        .clickable(true)
                        .visible(true);
                break;
            default:
                break;
            case CAMPUSCENTERWALLS:
                polygonPositions = new PolygonOptions()
                        .add(new LatLng(44.324745, -93.970942))
                        .add(new LatLng(44.324865, -93.971012))
                        .add(new LatLng(44.324625, -93.971208))
                        .add(new LatLng(44.324505, -93.971138))
                        .add(new LatLng(44.324547, -93.971275))
                        .add(new LatLng(44.324667, -93.971345))
                        .add(new LatLng(44.324577, -93.971401))
                        .add(new LatLng(44.324457, -93.971331))
                        .add(new LatLng(44.324296, -93.971063))
                        .add(new LatLng(44.324416, -93.971133))
                        .add(new LatLng(44.324291, -93.971224))
                        .add(new LatLng(44.324171, -93.971154))
                        .add(new LatLng(44.324117, -93.971054))
                        .add(new LatLng(44.324237, -93.971124))
                        .add(new LatLng(44.323988, -93.971317))
                        .add(new LatLng(44.323868, -93.971247))
                        .add(new LatLng(44.323711, -93.970861))
                        .add(new LatLng(44.323831, -93.970931))
                        .add(new LatLng(44.324574, -93.970405))
                        .add(new LatLng(44.324454, -93.970335))
                        .add(new LatLng(44.324745, -93.970942))
                        .add(new LatLng(44.324865, -93.971012))
                        .strokeWidth(2)
                        .visible(true)
                        .fillColor(Color.parseColor("#B366ff66"));
        }
        return polygonPositions;
    }
}
