package com.example.huyviet1995.gustavusmapproject;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import org.w3c.dom.Text;

/**
 * Created by huyviet1995 on 6/27/16.
 */
public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private View mWindows;
    private View mContents;
    CustomInfoWindowAdapter(FragmentActivity activity) {
        mWindows = activity.getLayoutInflater().inflate(R.layout.custom_info_windows,null);
        mContents= activity.getLayoutInflater().inflate(R.layout.custom_info_contents,null);
    }
    @Override
    public View getInfoWindow(Marker marker) {
        process(marker,mWindows);
        return mWindows;
    }
    @Override
    public View getInfoContents(Marker marker) {
        process(marker,mContents);
        return mContents;
    }
    private void process(Marker marker, View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.info_window_image);
        imageView.setImageResource(R.drawable.ic_action_name);
        TextView titleText = (TextView) view.findViewById(R.id.title);
        String title = marker.getTitle();
        if (title!=null) {
            SpannableString titleString = new SpannableString(title);
            titleString.setSpan(new ForegroundColorSpan(Color.BLUE),0,title.length(),0);
            titleText.setText(titleString);
        }
        else
            titleText.setText("");

        TextView snippetText = (TextView) view.findViewById(R.id.content);
        String snippet = marker.getSnippet();
        if (snippet != null) {
            SpannableString snippetString = new SpannableString(snippet);
            snippetString.setSpan(new ForegroundColorSpan(Color.RED),0,snippet.length(),0);
            snippetText.setText(snippetString);
        }
        else
            snippetText.setText("");

    }
}
