package com.eczane.eczanebitirme.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.eczane.eczanebitirme.R;
import com.eczane.eczanebitirme.activities.ListViewActivity;
import com.eczane.eczanebitirme.helpers.PermissionsManager;
import com.eczane.eczanebitirme.helpers.Storage;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Locale;

/**
 * @author Cehver V. Karakoc
 */

public class SearchFragment extends Fragment implements TextView.OnEditorActionListener, View.OnClickListener {
    private FusedLocationProviderClient mFusedLocationClient;
    Geocoder geocoder;
    private EditText editTextSearch;
    private Storage storage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_search, container, false);

        storage = new Storage(getActivity(),"Eczane");

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        geocoder = new Geocoder(getActivity(), Locale.getDefault());

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        editTextSearch = (EditText) view.findViewById(R.id.edit_text_search);
        editTextSearch.setOnEditorActionListener(this);

        view.findViewById(R.id.button_nearest).setOnClickListener(this);

        super.onViewCreated(view, savedInstanceState);
    }

    public void onClick(View view){
        findNearest(view);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_SEARCH) {
            String keyword = v.getText().toString();
            storage.addLastSearch(keyword);

            search(keyword, 0,0);
            return true;
        }
        return false;
    }

    public void findNearest(@Nullable View view) {
        if(PermissionsManager.checkLocationPermission(getActivity()))
        {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                search("localbase", location.getLatitude(), location.getLongitude());
                            } else {
                                Log.d("myLOCATION", "null");
                            }
                        }
                    });
        }
    }
    private void search(String keyword, double lat, double lng) {
        Intent intent = new Intent(getActivity(), ListViewActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("keyword", keyword);
        bundle.putDouble("lat", lat);
        bundle.putDouble("lng", lng);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
