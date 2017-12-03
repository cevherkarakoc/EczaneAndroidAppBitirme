package com.eczane.eczanebitirme.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.eczane.eczanebitirme.R;
import com.eczane.eczanebitirme.activities.ListViewActivity;
import com.eczane.eczanebitirme.adapters.Search.SearchAdapter;
import com.eczane.eczanebitirme.helpers.ClickListener;
import com.eczane.eczanebitirme.helpers.RecyclerTouchListener;
import com.eczane.eczanebitirme.helpers.Storage;

/**
 * @author Cehver V. Karakoc
 */

public class LastSearchesFragment extends Fragment implements ClickListener{
    Storage storage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_last_searches, container, false);

        storage = new Storage(getContext(),"Eczane");

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView search_list = (RecyclerView) view.findViewById(R.id.recycler_searches);

        SearchAdapter adapter = new SearchAdapter(storage.getLastSearches());
        search_list.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL, false);
        search_list.setLayoutManager(layoutManager);

        search_list.setItemAnimator(new DefaultItemAnimator());

        search_list.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),search_list,this));

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(getActivity(), ListViewActivity.class);

        startActivity(intent);
    }

    @Override
    public void onLongClick(View view, int position) {
        //onClick(view, position);
    }
}
