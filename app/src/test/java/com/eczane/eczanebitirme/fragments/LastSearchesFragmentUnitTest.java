package com.eczane.eczanebitirme.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eczane.eczanebitirme.R;
import com.eczane.eczanebitirme.activities.ListViewActivity;
import com.eczane.eczanebitirme.helpers.Storage;
import com.eczane.eczanebitirme.models.SearchRecord;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowApplication;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.robolectric.util.FragmentTestUtil.startFragment;

/**
 * UnitTest for LastSearchesFragment Class
 *
 * @author Cehver V. Karakoc
 */

@RunWith(RobolectricTestRunner.class)
public class LastSearchesFragmentUnitTest {
    private Context context;
    private View view;
    private LastSearchesFragment fragment;
    private ArrayList<SearchRecord> searchRecords;

    @Before
    public void initFragment() {
        context = RuntimeEnvironment.application;
        fragment = new LastSearchesFragment();

        Storage storage = new Storage(context,"Eczane");
        for(int i = 1; i<= 5; i++) storage.addLastSearch("Search"+i);

        searchRecords = storage.getLastSearches();

        startFragment(fragment);

        view = fragment.getView();
    }

    @Test
    public void clickingARecord_shouldOpenListViewActivity() {
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_searches);

        recyclerView.measure(0, 0);
        recyclerView.layout(0, 0, 1000, 1000);

        fragment.onClick(recyclerView.getChildAt(0), 0);

        Intent expectedIntent = new Intent(context,ListViewActivity.class);
        Intent observedIntent = ShadowApplication.getInstance().getNextStartedActivity();


        assertThat("List View doesn't Open",observedIntent.getComponent(), is(expectedIntent.getComponent()));
    }
}
