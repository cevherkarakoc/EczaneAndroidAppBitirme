package com.eczane.eczanebitirme.helpers;

import android.view.View;

/**
 * @author Cehver V. Karakoc
 */

public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}

