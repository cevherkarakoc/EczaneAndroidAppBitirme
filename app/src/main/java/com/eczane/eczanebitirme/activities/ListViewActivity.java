package com.eczane.eczanebitirme.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.eczane.eczanebitirme.models.Pharmacy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Cehver V. Karakoc
 */
public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private FragmentManager fragmentManager;
    private PharmacyCardAdapter adapter;

    private ArrayList<Pharmacy> pharmacies = new ArrayList<>();
    private ArrayList<Pharmacy> sentryPharmacies = new ArrayList<>();

    private boolean filterSentry = false;
    private int colorMoon;
    private int colorGrey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        colorMoon = ContextCompat.getColor(this, R.color.colorMoon);
        colorGrey = ContextCompat.getColor(this, R.color.colorGrey);

        fetchPharmanciesList();

        adapter = new PharmacyCardAdapter(this, pharmacies);
        this.setUpListView(adapter);

        fragmentManager = getFragmentManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_list_view, menu);

        this.setUpSentryAction(menu.findItem(R.id.action_sentry));

        return true;
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
        Pharmacy p = filterSentry ?  sentryPharmacies.get(position) : pharmacies.get(position);
        pdFragment.setPharmacy(p);

        fragmentManager.beginTransaction().add(R.id.rootLayout, pdFragment).addToBackStack("detail").commit();
    }

    private Response.Listener<Pharmacy[]> createSuccessListener() {
        return new Response.Listener<Pharmacy[]>() {
            @Override
            public void onResponse(Pharmacy[] response) {
                pharmacies.addAll(Arrays.asList(response));

                for (Pharmacy pharmacy : pharmacies) {
                    if(pharmacy.isSentry()) sentryPharmacies.add(pharmacy);
                }

                adapter.notifyDataSetChanged();
            }
        };
    }

    private Response.ErrorListener createErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("REQ",error.getMessage());
                Toast.makeText(ListViewActivity.this, "HATA",Toast.LENGTH_LONG).show();
            }
        };
    }

    private void fetchPharmanciesList(){
        RequestHandler.fetchPharmanciesList(
                this,
                createSuccessListener(),
                createErrorListener()
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
}
