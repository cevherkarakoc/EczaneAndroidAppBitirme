package com.eczane.eczanebitirme.adapters.Search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eczane.eczanebitirme.R;
import com.eczane.eczanebitirme.models.SearchRecord;

import java.util.ArrayList;

/**
 * Adapter for Search Records
 *
 * @author Cehver V. Karakoc
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchHolder> {

    private ArrayList<SearchRecord> searches;

    public SearchAdapter(ArrayList<SearchRecord> searches){
        this.searches = searches;
    }

    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_record, parent, false);

        return new SearchHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, int position) {
        holder.title.setText(searches.get(position).getKey());
    }

    @Override
    public int getItemCount() {
        return searches.size();
    }

    public SearchRecord getItem(int position) {
        return searches.get(position);
    }
}
