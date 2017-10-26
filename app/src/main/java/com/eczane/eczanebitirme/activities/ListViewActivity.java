package com.eczane.eczanebitirme.activities;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.eczane.eczanebitirme.R;
import com.eczane.eczanebitirme.adapters.PharmancyCardAdapter;
import com.eczane.eczanebitirme.fragments.PharmancyDetailFragment;
import com.eczane.eczanebitirme.models.Dummy;
import com.eczane.eczanebitirme.models.Pharmacy;

public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    FragmentManager fragmentManager;
    Pharmacy[] pharmacies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ListView pharList = (ListView) findViewById(R.id.phar_list);

        pharmacies = Dummy.alist;
        PharmancyCardAdapter adapter = new PharmancyCardAdapter(this, pharmacies);

        pharList.setAdapter(adapter);
        pharList.setOnItemClickListener(this);

        fragmentManager = getFragmentManager();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PharmancyDetailFragment pdFragment = new PharmancyDetailFragment();
        pdFragment.setPharmancy(pharmacies[position]);

        fragmentManager.beginTransaction().add(R.id.rootLayout, pdFragment).addToBackStack("detail").commit();
    }
}
