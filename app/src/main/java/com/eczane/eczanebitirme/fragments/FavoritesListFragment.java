package com.eczane.eczanebitirme.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.eczane.eczanebitirme.R;
import com.eczane.eczanebitirme.adapters.PharmacyCard.PharmacyCardAdapter;
import com.eczane.eczanebitirme.helpers.Storage;
import com.eczane.eczanebitirme.models.Pharmacy;

import java.util.ArrayList;

/**
 * @author Cehver V. Karakoc
 */

public class FavoritesListFragment extends Fragment implements AdapterView.OnItemClickListener, FragmentManager.OnBackStackChangedListener {
    FragmentManager fragmentManager;
    Storage storage;
    ListView listView;
    TextView noFavoritesText;
    PharmacyCardAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_favorites, container, false);

        fragmentManager = getFragmentManager();

        storage = new Storage(getContext(),"Eczane");

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        listView = (ListView) view.findViewById(R.id.favorites_list);
        noFavoritesText = (TextView) view.findViewById(R.id.no_favorites);

        adapter = new PharmacyCardAdapter(getContext(), new ArrayList<Pharmacy>());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onStart() {
        refreshList();

        super.onStart();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PharmacyDetailFragment pdFragment = new PharmacyDetailFragment();
        Pharmacy p = (Pharmacy) parent.getAdapter().getItem(position);
        pdFragment.setPharmacy(p);

        fragmentManager.beginTransaction().add(R.id.homeLayout, pdFragment,p.getTitle()).addToBackStack("detail").commit();

        fragmentManager.addOnBackStackChangedListener(this);
    }

    @Override
    public void onBackStackChanged() {
        refreshList();
    }

    private void refreshList(){
        ArrayList<Pharmacy> favorites = new ArrayList<>( storage.getFavorites().values() );

        adapter.changeDataSet(favorites);

        if(favorites.size() == 0){
            noFavoritesText.setVisibility(View.VISIBLE);
        } else {
            noFavoritesText.setVisibility(View.INVISIBLE);
        }

    }

}
