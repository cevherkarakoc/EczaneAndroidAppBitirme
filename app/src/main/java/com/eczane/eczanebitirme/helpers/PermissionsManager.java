package com.eczane.eczanebitirme.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.eczane.eczanebitirme.activities.HomeActivity;

/**
 * @author Cehver V. Karakoc
 */

public class PermissionsManager {

    public static boolean checkLocationPermission(Activity activity) {
        int permissionCheck = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION);
        Log.d("PERMIS", "KOD : " + permissionCheck);

        if(permissionCheck == PackageManager.PERMISSION_DENIED) {
            Log.d("PERMIS", "KOD2 : " + permissionCheck);
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, HomeActivity.LOC_PER_RUN);

            return false;
        }
        return true;
    }
}
