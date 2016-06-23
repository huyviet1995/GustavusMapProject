package com.example.huyviet1995.gustavusmapproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by huyviet1995 on 6/22/16.
 */
public class GoogleMapFragment extends Fragment implements OnMapReadyCallback{

    private GoogleMap mMap;

    private MapView mMapView;

    private static final LatLng CampusCenter = new LatLng(44.324341,-93.970694);

    private static final LatLng LundCenter = new LatLng(44.325294,-93.971832);

    private static final LatLng Library = new LatLng(44.323505,-93.972158);

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
        changeCamera(CameraUpdateFactory.newLatLngZoom(Gustavus,15f));
        /*Set the clickable buttons on the text view*/
        TextView mCampusCenter = (TextView) v.findViewById(R.id.campus_center);
        TextView mLibrary = (TextView) v.findViewById(R.id.library);
        TextView mLundCenter = (TextView) v.findViewById(R.id.lund_center);
        mCampusCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCampusCenter(view);
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
        return v;
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        /*Add the first marker to Gustavus College*/

    }
    public void showCampusCenter(View v) {
        /*wait until the map is ready*/
        if (mMap == null)
            return;
        changeCamera(CameraUpdateFactory.newLatLngZoom(CampusCenter,18f));
    }

    public void showLundCenter(View v) {
        //wait until map is ready
        if (mMap == null) {
            return;
        }
        changeCamera(CameraUpdateFactory.newLatLngZoom(LundCenter,18f));
    }

    public void showLibrary(View v) {
        //wait until map is ready
        if (mMap == null) {
            return;
        }
        changeCamera(CameraUpdateFactory.newLatLngZoom(Library,18f));
    }

    private boolean checkReady() {
        if (mMap == null) {
            Toast.makeText(getActivity(), "Waiting for map to show up", Toast.LENGTH_SHORT).show();
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
}
