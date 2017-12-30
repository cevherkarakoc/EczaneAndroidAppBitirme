package com.eczane.eczanebitirme.activities;

import android.app.FragmentManager;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.eczane.eczanebitirme.R;
import com.eczane.eczanebitirme.fragments.SearchFragment;

/**
 * @author Cehver V. Karakoc
 */
public class HomeActivity extends AppCompatActivity {
    public static final int LOC_PER_RUN = 1;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }

        fragmentManager = getFragmentManager();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case HomeActivity.LOC_PER_RUN: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SearchFragment searchFragment = (SearchFragment)fragmentManager.findFragmentById(R.id.fragment_search);
                    searchFragment.findNearest(null);
                }
            }
        }
    }
}
