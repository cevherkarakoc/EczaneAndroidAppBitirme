package com.eczane.eczanebitirme.activities;

import android.app.FragmentManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.eczane.eczanebitirme.R;
import com.eczane.eczanebitirme.adapters.PharmacyCard.PharmacyCardAdapter;
import com.eczane.eczanebitirme.fragments.PharmacyDetailFragment;
import com.eczane.eczanebitirme.helpers.HTTPRequest.RequestHandler;
import com.eczane.eczanebitirme.helpers.Storage;
import com.eczane.eczanebitirme.models.Pharmacy;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Cehver V. Karakoc
 */
public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private FragmentManager fragmentManager;
    private PharmacyCardAdapter adapter;

    private Storage storage;
    private ArrayList<Pharmacy> pharmacies = new ArrayList<>();
    private ArrayList<Pharmacy> sentryPharmacies = new ArrayList<>();

    private ListView listView;
    private AVLoadingIndicatorView loadingView;

    double lat;
    double lng;
    private boolean localbase = false;
    private boolean filterSentry = false;
    private int colorMoon;
    private int colorGrey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        colorMoon = ContextCompat.getColor(this, R.color.colorMoon);
        colorGrey = ContextCompat.getColor(this, R.color.colorGrey);

        listView = (ListView) findViewById(R.id.phar_list);
        loadingView = (AVLoadingIndicatorView) findViewById(R.id.list_loading);

        storage = new Storage(this,"Eczane");

        String keyword;
        try {
            Bundle bundle = getIntent().getExtras();
            keyword = bundle.getString("keyword", "31900");
            lat = bundle.getDouble("lat", 0);
            lng = bundle.getDouble("lng", 0);
        }catch (Exception ex) {
            keyword = "payas";
            lat = 0;
            lng = 0;
        }

        if(keyword.equals("localbase")){
            localbase = true;
            fetchPharmaciesList(lat, lng);
        }else {
            fetchPharmaciesList(keyword);
        }

        adapter = new PharmacyCardAdapter(this, pharmacies);
        this.setUpListView(adapter);

        fragmentManager = getFragmentManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_list_view, menu);

        this.setUpSentryAction(menu.findItem(R.id.action_sentry));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sentry:
                filterSentry = !item.isChecked();
                this.setUpSentryAction(item);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PharmacyDetailFragment pdFragment = new PharmacyDetailFragment();
        Pharmacy p = (Pharmacy) parent.getAdapter().getItem(position);
        pdFragment.setPharmacy(p);

        fragmentManager.beginTransaction().add(R.id.rootLayout, pdFragment,p.getTitle()).addToBackStack("detail").commit();
    }

    private Response.Listener<Pharmacy[]> createSuccessListener() {
        return new Response.Listener<Pharmacy[]>() {
            @Override
            public void onResponse(Pharmacy[] response) {
                pushPharmaciesList(response);

                if(localbase) {
                    storage.cachePharmancies(response);
                }
            }
        };
    }

    private Response.ErrorListener createErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pushPharmaciesList(storage.getPharmancies());

                Toast.makeText(ListViewActivity.this, "Veriler Alınamadı.",Toast.LENGTH_LONG).show();
            }
        };
    }

    private void pushPharmaciesList(Pharmacy[] newPharmacies){
        pharmacies.addAll(Arrays.asList(newPharmacies));

        for (Pharmacy pharmacy : pharmacies) {
            if(localbase) {
                double dist = distanceOfLocation(pharmacy.getAddress().getLat(), pharmacy.getAddress().getLon(), lat, lng);
                pharmacy.setDist(dist);
            }
            if(pharmacy.isSentry()) sentryPharmacies.add(pharmacy);
        }
        adapter.notifyDataSetChanged();
        hideLoading();
    }

    private void fetchPharmaciesList(String keyword){
        RequestHandler.fetchPharmaciesList(
                this,
                createSuccessListener(),
                createErrorListener(),
                keyword
        );
    }

    private void fetchPharmaciesList(double lat, double lng){
        RequestHandler.fetchPharmaciesList(
                this,
                createSuccessListener(),
                createErrorListener(),
                lat,
                lng
        );
    }

    private void setUpListView(PharmacyCardAdapter _adapter) {
        ListView pharList = (ListView) findViewById(R.id.phar_list);
        pharList.setAdapter(_adapter);
        pharList.setOnItemClickListener(this);
    }

    private void setUpSentryAction(MenuItem sentryActionButton){
        adapter.changeDataSet(filterSentry ? sentryPharmacies : pharmacies);
        sentryActionButton.getIcon().setTint(filterSentry ? colorMoon : colorGrey);
        sentryActionButton.setChecked(filterSentry);
    }

    private void hideLoading(){
        loadingView.smoothToHide();

        listView.setVisibility(View.VISIBLE);
    }

    private void showLoading(){
        loadingView.smoothToShow();
        listView.setVisibility(View.GONE);
    }

    private double distanceOfLocation(double latA,double lngA,double latB,double lngB) {
        Location locationA = new Location("point A");

        locationA.setLatitude(latA);
        locationA.setLongitude(lngA);

        Location locationB = new Location("point B");

        locationB.setLatitude(latB);
        locationB.setLongitude(lngB);

        double distance = locationA.distanceTo(locationB);

        return distance / 1000;
    }
}
