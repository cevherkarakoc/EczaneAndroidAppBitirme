package com.eczane.eczanebitirme.adapters.Search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.eczane.eczanebitirme.R;

/**
 * @author Cehver V. Karakoc
 */

public class SearchHolder extends RecyclerView.ViewHolder {
    public TextView title;

    SearchHolder(View view) {
        super(view);
        title = (TextView) view.findViewById(R.id.search_title);
    }
}
