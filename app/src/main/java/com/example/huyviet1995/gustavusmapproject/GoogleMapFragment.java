package com.example.huyviet1995.gustavusmapproject;

import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowCloseListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Created by huyviet1995 on 6/22/16.
 */
public class GoogleMapFragment extends Fragment implements
        GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback,
        GoogleMap.OnInfoWindowClickListener,
        OnInfoWindowCloseListener
        {

    private GoogleMap mMap;

    private MapView mMapView;

    private boolean mPermissionDenied;

    private static final int REQUEST_CODE =1;

    private static final LatLng CampusCenter = new LatLng(44.324341, -93.970694);

    private static final LatLng LundCenter = new LatLng(44.325294, -93.971832);

    private static final LatLng Library = new LatLng(44.323505, -93.972158);

    private static final LatLng Gustavus = new LatLng(44.323286, -93.971139);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.google_map_fragment, container, false);
        /*Display the google map fragment*/
        mMapView = (MapView) v.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();// needed to get the map to display immediately
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMap = mMapView.getMap();
        mMap.setOnMyLocationButtonClickListener(this);
        enableMyLocation();
        TextView mCampusCenter = (TextView) v.findViewById(R.id.campus_center);
        TextView mLibrary = (TextView) v.findViewById(R.id.library);
        TextView mLundCenter = (TextView) v.findViewById(R.id.lund_center);
        /*Set everything on fire*/

        mMap.setOnMarkerClickListener(this);
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getActivity()));
        mCampusCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCampusCenter(view);
                boundCampusCenter();
            }
        });

        mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {

            @Override
            public void onPolygonClick(Polygon polygon) {
                polygon.setVisible(true);
                polygon.setStrokeColor(Color.BLUE);
                polygon.setStrokeWidth(2);
            }
        });

        mLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLibrary(view);
            }
        });
        mLundCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLundCenter(view);
            }
        });

        /*Set the adapter of the info windows*/

        return v;
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        /*Enable the location of the user on the map*/
        /*Set the location of the user*/

    }
    public void showCampusCenter(View v) {
        /*wait until the map is ready*/
        if (mMap == null)
            return;
        changeCamera(CameraUpdateFactory.newLatLngZoom(CampusCenter,18f));
        /*Pop up the marker of the campus center*/
        mMap.addMarker(new BuildingLocationData(BuildingLocationData.Place.CAMPUSCENTER).buildingLocation())
                .showInfoWindow();
    }

    public void showLundCenter(View v) {
        //wait until map is ready
        if (mMap == null) {
            return;
        }
        changeCamera(CameraUpdateFactory.newLatLngZoom(LundCenter,18f));
        /*Pop up the marker and the info window of the Lund Center*/
        mMap.addMarker(new BuildingLocationData(BuildingLocationData.Place.LUNDCENTER).buildingLocation())
                .showInfoWindow();
    }

    public void showLibrary(View v) {
        //wait until map is ready
        if (mMap == null) {
            return;
        }
        changeCamera(CameraUpdateFactory.newLatLngZoom(Library,18f));
    }
    /*Bound the camous center*/
    private void boundCampusCenter() {
        mMap.addPolygon(new BuildingLocationData(BuildingLocationData.Place.CAMPUSCENTER).locationDetails());
    }
    private boolean checkReady() {
        if (mMap == null) {
            Toast.makeText(getActivity(), "Waiting for map to appear", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /*Move camera to the specific place*/
    private void changeCamera(CameraUpdate update) {changeCamera(update,null);}
    /*Create the animation to move to the designated place when the button is clicked*/
    private void changeCamera (CameraUpdate update, GoogleMap.CancelableCallback callback) {
        mMap.animateCamera(update,callback);
    }
    /*This whole chunk of codes will be dedicated to enable location*/
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            PermissionRequests.requestPermission(getActivity(),REQUEST_CODE,Manifest.permission.ACCESS_FINE_LOCATION,true);
        }
        else if (mMap != null) {
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != REQUEST_CODE) {
            return;
        }

        if (PermissionRequests.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            mPermissionDenied = true;
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(getActivity(),"Click info windows",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onInfoWindowClose(Marker marker) {
        Toast.makeText(getActivity(),"Click content windows",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}
