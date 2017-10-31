package com.eczane.eczanebitirme.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.eczane.eczanebitirme.R;
import com.eczane.eczanebitirme.helpers.Storage;
import com.eczane.eczanebitirme.models.SearchRecord;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HomeActivity extends AppCompatActivity implements TextView.OnEditorActionListener {
    private EditText editTextSearch;
    Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }

        editTextSearch = (EditText) findViewById(R.id.edit_text_search);
        editTextSearch.setOnEditorActionListener(this);

        storage = new Storage(this,"Eczane");
    }

    public void findNearest(View view) {
        search();
    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_SEARCH) {
            storage.addLastSearch(v.getText().toString());
            ArrayList<SearchRecord> lastSearches = storage.getLastSearches();

            if(v.getText().toString().equals("sil")) {
                storage.clearLastSearches();
            }
            if(!lastSearches.isEmpty()) {
                Log.d("STORAGE", lastSearches.toString());
            } else {
                Log.d("STORAGE", "Empty");
            }

            search();
            return true;
        }
        return false;
    }

    private void search() {
        Intent intent = new Intent(this, ListViewActivity.class);

        startActivity(intent);
    }


}
