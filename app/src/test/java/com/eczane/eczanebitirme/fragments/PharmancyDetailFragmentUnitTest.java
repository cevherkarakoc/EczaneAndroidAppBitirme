package com.eczane.eczanebitirme.fragments;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.eczane.eczanebitirme.R;
import com.eczane.eczanebitirme.models.Address;
import com.eczane.eczanebitirme.models.Pharmacy;
import com.eczane.eczanebitirme.models.Zone;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowApplication;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.robolectric.util.FragmentTestUtil.startFragment;

/**
 * UnitTest for PharmancyDetailFragment Class
 *
 * @author Cehver V. Karakoc
 */

@RunWith(RobolectricTestRunner.class)
public class PharmancyDetailFragmentUnitTest {
    private PharmancyDetailFragment fragment;
    private View view;
    private Pharmacy pharmacy;

    @Before
    public void initFragment() {
        fragment = new PharmancyDetailFragment();

        Zone testZone = new Zone(0, "Test Zone");
        Address testAddress = new Address("Test Address",13,7);

        pharmacy = new Pharmacy
                ("Test Title","000", testZone, testZone,testAddress);

        fragment.setPharmancy(pharmacy);
        startFragment(fragment);

        view = fragment.getView();
    }

    @Test
    public void validateTitle() {
        TextView title = (TextView) view.findViewById(R.id.pharTitle);

        String expectedTitle = pharmacy.getTitle();
        String observedTitle = title.getText().toString();

        assertThat("Title View is null",title, is(notNullValue()));
        assertThat("Title View contains incorrect text",observedTitle, is(expectedTitle));
    }

    @Test
    public void validatePhone() {
        TextView phone = (TextView) view.findViewById(R.id.pharPhone);

        String expectedPhone = pharmacy.getPhone();
        String observedPhone = phone.getText().toString();

        assertThat("Title View is null",phone, is(notNullValue()));
        assertThat("Title View contains incorrect text",observedPhone, is(expectedPhone));
    }

    @Test
    public void validateAddress() {
        TextView address = (TextView) view.findViewById(R.id.pharAddress);

        String expectedAddress = pharmacy.getAddress().getText();
        String observedAddress = address.getText().toString();

        assertThat("Title View is null",address, is(notNullValue()));
        assertThat("Title View contains incorrect text",observedAddress, is(expectedAddress));
    }

    @Test
    public void clickingCallButton_shouldStartDialIntent(){
        Button callButton = (Button) view.findViewById(R.id.pharCallButton);
        callButton.performClick();

        Intent expectedIntent = new Intent(Intent.ACTION_DIAL);
        Intent observedIntent = ShadowApplication.getInstance().getNextStartedActivity();

        assertThat(observedIntent.getComponent(), is(expectedIntent.getComponent()));
    }

    @Test
    public void clickingMapButton_shouldStartMapIntent(){
        Button  mapButton = (Button) view.findViewById(R.id.pharMapButton);
        mapButton.performClick();

        Intent expectedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0"));
        Intent observedIntent = ShadowApplication.getInstance().getNextStartedActivity();

        assertThat(observedIntent.getComponent(), is(expectedIntent.getComponent()));
    }
}
