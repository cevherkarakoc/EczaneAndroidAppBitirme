package com.eczane.eczanebitirme.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.eczane.eczanebitirme.R;

public class HomeActivity extends AppCompatActivity implements TextView.OnEditorActionListener {
    private EditText editTextSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setElevation(0);
        editTextSearch = (EditText) findViewById(R.id.edit_text_search);
        editTextSearch.setOnEditorActionListener(this);
    }

    public void findNearest(View view) {
        search();
    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_SEARCH) {
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
