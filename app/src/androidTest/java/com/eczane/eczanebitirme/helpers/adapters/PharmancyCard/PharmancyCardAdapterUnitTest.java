package com.eczane.eczanebitirme.helpers.adapters.PharmancyCard;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.eczane.eczanebitirme.adapters.PharmacyCard.PharmacyCardAdapter;
import com.eczane.eczanebitirme.models.Address;
import com.eczane.eczanebitirme.models.Pharmacy;
import com.eczane.eczanebitirme.models.Zone;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * UnitTest for PharmancyCardAdapter Class
 *
 * @author Cehver V. Karakoc
 */

@RunWith(AndroidJUnit4.class)
public class PharmancyCardAdapterUnitTest {
    private final String ONE = "ONE";
    private final String TWO = "TWO";

    private PharmacyCardAdapter adapter;

    @Before
    public void initAdapter(){
        Context instrumentationCtx = InstrumentationRegistry.getContext();

        Zone testZone = new Zone(0, "test");
        Address testAddress = new Address("Test",0,0);

        Pharmacy pharmacyOne = new Pharmacy(ONE,"000", testZone, testZone,testAddress);
        Pharmacy pharmacyTwo = new Pharmacy(TWO,"000", testZone, testZone,testAddress);

        ArrayList<Pharmacy> pharmacies = new ArrayList<>();
        pharmacies.add(pharmacyOne);
        pharmacies.add(pharmacyTwo);

        adapter = new PharmacyCardAdapter(instrumentationCtx, pharmacies);
    }

    @Test
    public void testGetItem(){
        Pharmacy itemOne = adapter.getItem(0);
        Pharmacy itemTwo = adapter.getItem(1);

        assertThat("ONE was expected",itemOne.getTitle(), is(ONE));
        assertThat("TWO was expected",itemTwo.getTitle(), is(TWO));
    }

    @Test
    public void testGetItemId(){
        long idONE = adapter.getItemId(0);
        long idTWO = adapter.getItemId(1);

        long expectedIDONE = 0;
        long expectedIDTWO = 1;

        assertThat("Wrong ID",idONE, is(expectedIDONE));
        assertThat("Wrong ID",idTWO, is(expectedIDTWO));
    }

    @Test
    public void testGetCount(){
        int count = adapter.getCount();
        int expectedCount = 2;

        assertThat("Wrong length", count, is(expectedCount));
    }
}
