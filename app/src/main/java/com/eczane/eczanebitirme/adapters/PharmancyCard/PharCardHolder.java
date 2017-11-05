package com.eczane.eczanebitirme.adapters.PharmancyCard;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eczane.eczanebitirme.R;

/**
 * @author Cehver V. Karakoc
 */
class PharCardHolder {
    private TextView title;
    private TextView zone;
    private TextView phone;
    private ImageView sentry;

    PharCardHolder (View v) {
        title = (TextView)v.findViewById(R.id.phar_title);
        zone = (TextView)v.findViewById(R.id.phar_zone);
        phone = (TextView)v.findViewById(R.id.phar_phone);
        sentry = (ImageView)v.findViewById(R.id.phar_sentry);
    }

    TextView getTitle() {
        return title;
    }

    TextView getZone() {
        return zone;
    }

    TextView getPhone() {
        return phone;
    }

    ImageView getSentry() {
        return sentry;
    }
}
