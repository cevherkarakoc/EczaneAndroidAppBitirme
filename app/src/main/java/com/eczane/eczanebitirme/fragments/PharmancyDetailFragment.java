package com.eczane.eczanebitirme.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.eczane.eczanebitirme.R;
import com.eczane.eczanebitirme.models.Pharmacy;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * @author Cehver V. Karakoc
 */
public class PharmancyDetailFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {
    Pharmacy pharmancy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_pharmancy_detail, container, false);

        TextView pharTitle = (TextView) view.findViewById(R.id.pharTitle);
        TextView pharAddress = (TextView) view.findViewById(R.id.pharAddress);
        Button pharPhone = (Button) view.findViewById(R.id.pharPhone);

        MapView pharMap = (MapView) view.findViewById(R.id.pharMap);

        pharMap.onCreate(savedInstanceState);
        pharMap.onResume();
        pharMap.getMapAsync(this);

        pharTitle.setText(pharmancy.getTitle());
        pharAddress.setText(pharmancy.getAddress().getText());
        pharPhone.setText(pharmancy.getPhone());
        pharPhone.setOnClickListener(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng point = new LatLng(pharmancy.getAddress().getLat(), pharmancy.getAddress().getLon());
        Marker marker = googleMap.addMarker(new MarkerOptions().position(point)
                .title(pharmancy.getTitle()));
        marker.showInfoWindow();
        googleMap.getUiSettings().setMapToolbarEnabled(true);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15));
    }

    public void setPharmancy(Pharmacy pharmancy) {
        this.pharmancy = pharmancy;
    }

    public void onClick(View v) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+pharmancy.getPhone()));
        startActivity(callIntent);
    }
}
