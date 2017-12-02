package com.eczane.eczanebitirme.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.eczane.eczanebitirme.R;

/**
 * @author Cehver V. Karakoc
 */
public class HomeActivity extends AppCompatActivity {

    //TODO: Son 3 arama yatay kaydırmalı olarak gösterilecek
    //TODO: Favori Eczaneler görüntülecenek
    //TODO: Konuma göre bulma eklenecek

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
    }
}
