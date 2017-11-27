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
import com.eczane.eczanebitirme.adapters.PharmancyCard.PharmancyCardAdapter;
import com.eczane.eczanebitirme.fragments.PharmancyDetailFragment;
import com.eczane.eczanebitirme.helpers.HTTPRequest.RequestHandler;
import com.eczane.eczanebitirme.models.Pharmacy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Cehver V. Karakoc
 */
public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    FragmentManager fragmentManager;
    PharmancyCardAdapter adapter;
    ArrayList<Pharmacy> pharmacies;
    ArrayList<Pharmacy> sentryPharmacies;
    boolean filterSentry = false;

    int colorMoon;
    int colorGrey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        colorMoon = ContextCompat.getColor(this, R.color.colorMoon);
        colorGrey = ContextCompat.getColor(this, R.color.colorGrey);

        ListView pharList = (ListView) findViewById(R.id.phar_list);

        RequestHandler.fetchPharmanciesList(
                this,
                createSuccessListener(),
                createErrorListener()
        );

        pharmacies = new ArrayList<>();
        sentryPharmacies = new ArrayList<>();

        adapter = new PharmancyCardAdapter(this, pharmacies);

        pharList.setAdapter(adapter);
        pharList.setOnItemClickListener(this);

        fragmentManager = getFragmentManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_list_view, menu);

        menu.findItem(R.id.action_sentry).getIcon().setTint(colorGrey);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sentry:
                filterSentry = !item.isChecked();

                adapter.changeDataSet(filterSentry ? sentryPharmacies : pharmacies);
                item.getIcon().setTint(filterSentry ? colorMoon : colorGrey);
                item.setChecked(filterSentry);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PharmancyDetailFragment pdFragment = new PharmancyDetailFragment();
        Pharmacy p = filterSentry ?  sentryPharmacies.get(position) : pharmacies.get(position);
        pdFragment.setPharmancy(p);

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
}
