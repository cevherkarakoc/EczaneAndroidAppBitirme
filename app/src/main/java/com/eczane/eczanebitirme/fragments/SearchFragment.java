package com.eczane.eczanebitirme.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.eczane.eczanebitirme.R;
import com.eczane.eczanebitirme.activities.ListViewActivity;
import com.eczane.eczanebitirme.helpers.Storage;
import com.eczane.eczanebitirme.models.SearchRecord;

import java.util.ArrayList;

/**
 * @author Cehver V. Karakoc
 */

public class SearchFragment extends Fragment implements TextView.OnEditorActionListener, View.OnClickListener {
    private EditText editTextSearch;
    private Storage storage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_search, container, false);

        storage = new Storage(getActivity(),"Eczane");

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
            storage.addLastSearch(v.getText().toString());
            ArrayList<SearchRecord> lastSearches = storage.getLastSearches();

            search();
            return true;
        }
        return false;
    }

    private void findNearest(View view) {
        search();
    }
    private void search() {
        Intent intent = new Intent(getActivity(), ListViewActivity.class);

        startActivity(intent);
    }
}
