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
import com.eczane.eczanebitirme.helpers.Storage;
import com.eczane.eczanebitirme.models.Pharmacy;
import com.willowtreeapps.spruce.animation.DefaultAnimations;

import java.util.Locale;

/**
 * @author Cehver V. Karakoc
 */
public class PharmacyDetailFragment extends Fragment implements View.OnClickListener {
    private Pharmacy pharmacy;
    private Storage storage;
    Button pharFavButton;
    private boolean isFav = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_pharmancy_detail, container, false);

        storage = new Storage(getContext(), "Eczane");

        TextView pharTitle = (TextView) view.findViewById(R.id.pharTitle);
        TextView pharPhone = (TextView) view.findViewById(R.id.pharPhone);
        TextView pharAddress = (TextView) view.findViewById(R.id.pharAddress);

        Button pharCallButton = (Button) view.findViewById(R.id.pharCallButton);
        Button pharMapButton = (Button) view.findViewById(R.id.pharMapButton);
        pharFavButton = (Button) view.findViewById(R.id.pharFavButton);

        pharTitle.setText(pharmacy.getTitle());
        pharPhone.setText(pharmacy.getPhone());
        pharAddress.setText(pharmacy.getAddress().getText());

        pharCallButton.setOnClickListener(this);
        pharMapButton.setOnClickListener(this);
        pharFavButton.setOnClickListener(this);

        if(storage.getFavoritesByKey(""+pharmacy.getID()) != null) {
            isFav = true;
        }
        toggleFavButton();

        return view;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.pharCallButton): startCall(); break;
            case (R.id.pharMapButton): openMap(); break;
            case (R.id.pharFavButton): handleFavorites(); break;
            default: break;
        }
    }

    private void startCall() {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+pharmacy.getPhone()));
        startActivity(callIntent);
    }

    private void openMap() {
        String uri = String.format(
                Locale.ENGLISH, "https://www.google.com/maps/dir/?api=1&destination=%f,%f",
                pharmacy.getAddress().getLat(),
                pharmacy.getAddress().getLon()
        );
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    private void handleFavorites() {
        if(isFav) removeFavorites();
        else addFavorites();

        isFav = !isFav;
        toggleFavButton();
    }

    private void removeFavorites(){
        storage.removeFavorites(pharmacy);
    }

    private void addFavorites() {
        storage.addFavorites(pharmacy);
    }

    private void toggleFavButton() {
        if(isFav) pharFavButton.setText(R.string.remove_fav);
        else pharFavButton.setText(R.string.add_fav);
    }

    @Override
    public void onStart() {
        super.onStart();

        DefaultAnimations.shrinkAnimator(getView(),150).start();
    }
}
