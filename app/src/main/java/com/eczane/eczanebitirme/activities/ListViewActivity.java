package com.eczane.eczanebitirme.activities;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ListView pharList = (ListView) findViewById(R.id.phar_list);

        RequestHandler.fetchPharmanciesList(
                this,
                createSuccessListener(),
                createErrorListener()
        );

        pharmacies = new ArrayList<>();
        adapter = new PharmancyCardAdapter(this, pharmacies);

        pharList.setAdapter(adapter);
        pharList.setOnItemClickListener(this);

        fragmentManager = getFragmentManager();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PharmancyDetailFragment pdFragment = new PharmancyDetailFragment();
        pdFragment.setPharmancy(pharmacies.get(position));

        fragmentManager.beginTransaction().add(R.id.rootLayout, pdFragment).addToBackStack("detail").commit();
    }

    private Response.Listener<Pharmacy[]> createSuccessListener() {
        return new Response.Listener<Pharmacy[]>() {
            @Override
            public void onResponse(Pharmacy[] response) {
                pharmacies.addAll(Arrays.asList(response));
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
