package com.eczane.eczanebitirme.activities;

import android.app.FragmentManager;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.eczane.eczanebitirme.R;
import com.eczane.eczanebitirme.adapters.PharmacyCard.PharmacyCardAdapter;
import com.eczane.eczanebitirme.fragments.PharmacyDetailFragment;
import com.eczane.eczanebitirme.models.Address;
import com.eczane.eczanebitirme.models.Pharmacy;
import com.eczane.eczanebitirme.models.Zone;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.robolectric.Shadows.shadowOf;

/**
 * UnitTest for ListViewActivity Class
 *
 * @author Cehver V. Karakoc
 */

@RunWith(RobolectricTestRunner.class)
public class ListViewActivityUnitTest {
    private ListViewActivity activity;
    private Context context;
    private FragmentManager fragmentManager;
    private Pharmacy pharmacy;

    @Before
    public void initActivity() {
        context = RuntimeEnvironment.application;
        activity = Robolectric.buildActivity(ListViewActivity.class)
                .create()
                .start()
                .resume()
                .visible()
                .get();

        Zone testZone = new Zone(0, "Test Zone");
        Address testAddress = new Address("Test Address",13,7);

        pharmacy = new Pharmacy
                ("Test Title","000", testZone, testZone,testAddress);

        fragmentManager = activity.getFragmentManager();
    }

    @Test
    public void onCreate_shouldInflateMenu() {
        Menu menu = shadowOf(activity).getOptionsMenu();

        MenuItem sentryAction = menu.findItem(R.id.action_sentry);
        String actionTitle = sentryAction.getTitle().toString();

        String expectedTitle = context.getString(R.string.only_sentry);

        assertThat("Menu is null", menu, is(notNullValue()));
        assertThat("Sentry Action is null", sentryAction, is(notNullValue()));
        assertThat("Sentry Action Title is Wrong", actionTitle, is(expectedTitle));
    }

    @Test
    public void selectingSentryAction_shouldCheck() {
        Menu menu = shadowOf(activity).getOptionsMenu();
        MenuItem sentryAction = menu.findItem(R.id.action_sentry);

        boolean expectedValueOne = true;
        boolean expectedValueTwo = false;

        activity.onOptionsItemSelected(sentryAction);
        boolean observedValueOne = sentryAction.isChecked();

        activity.onOptionsItemSelected(sentryAction);
        boolean observedValueTwo = sentryAction.isChecked();

        assertThat("Checking does not work",observedValueOne, is(expectedValueOne));
        assertThat("Unchecking doesn't work",observedValueTwo, is(expectedValueTwo));
    }

    @Test
    public void selectingSentryAction_shouldChangeData() {
        Menu menu = shadowOf(activity).getOptionsMenu();
        MenuItem sentryAction = menu.findItem(R.id.action_sentry);
        ListView list = (ListView)shadowOf(activity).findViewById(R.id.phar_list);

        ArrayList<Pharmacy> oldDataSet = ((PharmacyCardAdapter)list.getAdapter()).getDataSet();
        activity.onOptionsItemSelected(sentryAction);
        ArrayList<Pharmacy> dataSet = ((PharmacyCardAdapter)list.getAdapter()).getDataSet();

        assertThat("DataSets are Same", oldDataSet, not(sameInstance(dataSet)));
    }

    @Test
    public void clickingAListItem_shouldOpenDetailFragment() {
        ListView list = (ListView)shadowOf(activity).findViewById(R.id.phar_list);

        ArrayList<Pharmacy> pharmacies = new ArrayList<>();
        pharmacies.add(pharmacy);
        ((PharmacyCardAdapter)list.getAdapter()).changeDataSet(pharmacies);
        shadowOf(list).performItemClick(0);

        PharmacyDetailFragment fragment =
                (PharmacyDetailFragment) fragmentManager.findFragmentByTag(pharmacy.getTitle());

        assertThat(fragment, is(notNullValue()));
    }
}
