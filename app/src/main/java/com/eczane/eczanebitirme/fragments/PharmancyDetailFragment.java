package com.eczane.eczanebitirme.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.eczane.eczanebitirme.R;
import com.eczane.eczanebitirme.models.Pharmacy;

import java.util.Locale;

/**
 * @author Cehver V. Karakoc
 */
public class PharmancyDetailFragment extends Fragment implements View.OnClickListener {
    private Pharmacy pharmancy;
    private Button pharCallButton;
    private Button pharMapButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_pharmancy_detail, container, false);

        TextView pharTitle = (TextView) view.findViewById(R.id.pharTitle);
        TextView pharPhone = (TextView) view.findViewById(R.id.pharPhone);
        TextView pharAddress = (TextView) view.findViewById(R.id.pharAddress);

        Button pharCallButton = (Button) view.findViewById(R.id.pharCallButton);
        Button pharMapButton = (Button) view.findViewById(R.id.pharMapButton);


        pharTitle.setText(pharmancy.getTitle());
        pharPhone.setText(pharmancy.getPhone());
        pharAddress.setText(pharmancy.getAddress().getText());

        pharCallButton.setOnClickListener(this);
        pharMapButton.setOnClickListener(this);

        return view;
    }

    public void setPharmancy(Pharmacy pharmancy) {
        this.pharmancy = pharmancy;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.pharCallButton): startCall(); break;
            case (R.id.pharMapButton): openMap(); break;
            default: break;
        }
    }

    private void startCall() {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+pharmancy.getPhone()));
        startActivity(callIntent);
    }

    private void openMap() {
        String uri = String.format(
                Locale.ENGLISH, "https://www.google.com/maps/dir/?api=1&destination=%f,%f",
                pharmancy.getAddress().getLat(),
                pharmancy.getAddress().getLon()
        );
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }
}
