package com.eczane.eczanebitirme.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eczane.eczanebitirme.R;
import com.eczane.eczanebitirme.models.Pharmacy;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Adapter for Pharmancy Cards
 *
 * @author Cehver V. Karakoc
 */
public class PharmancyCardAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Pharmacy> pharmacies;

    public PharmancyCardAdapter(@NonNull Context context, ArrayList<Pharmacy> pharmacies) {
        this.context = context;
        this.pharmacies = pharmacies;
    }

    @Override
    public int getCount() {
        return pharmacies.size();
    }

    @Override
    public Pharmacy getItem(int position) {
        //şöyle de olabilir: public Object getItem(int position)
        return pharmacies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private class PharCardHolder {
        TextView title;
        TextView zone;
        TextView phone;
        ImageView sentry;

        PharCardHolder (View v) {
            title = (TextView)v.findViewById(R.id.phar_title);
            zone = (TextView)v.findViewById(R.id.phar_zone);
            phone = (TextView)v.findViewById(R.id.phar_phone);
            sentry = (ImageView)v.findViewById(R.id.phar_sentry);
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View card = convertView;
        PharCardHolder holder;

        if(card == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            card = layoutInflater.inflate(R.layout.pharmancy_card, parent, false);
            holder = new PharCardHolder(card);
            card.setTag(holder);
        } else {
            holder = (PharCardHolder) card.getTag();
        }



        Pharmacy p = pharmacies.get(position);
        holder.title.setText(p.getTitle());
        holder.zone.setText(p.getDistrict().getName() + ", " + p.getProvince().getName());
        holder.phone.setText(p.getPhone());

        if(p.isSentry()) {
            holder.sentry.setVisibility(View.VISIBLE);
        }else {
            holder.sentry.setVisibility(View.INVISIBLE);
        }

        return card;
    }
}
