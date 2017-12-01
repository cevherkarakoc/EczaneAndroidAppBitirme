package com.eczane.eczanebitirme.adapters.PharmacyCard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.eczane.eczanebitirme.R;
import com.eczane.eczanebitirme.models.Pharmacy;

import java.util.ArrayList;

/**
 * Adapter for Pharmancy Cards
 *
 * @author Cehver V. Karakoc
 */
public class PharmacyCardAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Pharmacy> pharmacies;

    public PharmacyCardAdapter(@NonNull Context context, ArrayList<Pharmacy> pharmacies) {
        this.context = context;
        this.pharmacies = pharmacies;
    }

    @Override
    public int getCount() {
        return pharmacies.size();
    }

    @Override
    public Pharmacy getItem(int position) {
        return pharmacies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PharCardHolder holder = initHolder(convertView, parent);
        setCardData(holder, position);
        return holder.getCard();
    }

    public ArrayList<Pharmacy> getDataSet() {
        return pharmacies;
    }

    public void changeDataSet(ArrayList<Pharmacy> dataSet){
        pharmacies = dataSet;
        notifyDataSetChanged();
    }

    private PharCardHolder initHolder(View card, ViewGroup parent){
        PharCardHolder holder;
        if(card == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            card = layoutInflater.inflate(R.layout.pharmancy_card, parent, false);
            holder = new PharCardHolder(card);
            card.setTag(holder);
        } else {
            holder = (PharCardHolder) card.getTag();
        }
        return holder;
    }

    private void setCardData(PharCardHolder holder, int position) {
        Pharmacy p = pharmacies.get(position);
        holder.getTitle().setText(p.getTitle());
        holder.getZone().setText(p.getZoneAsString());
        holder.getPhone().setText(p.getPhone());

        if(p.isSentry()) {
            holder.getSentry().setVisibility(View.VISIBLE);
        }else {
            holder.getSentry().setVisibility(View.INVISIBLE);
        }
    }
}
