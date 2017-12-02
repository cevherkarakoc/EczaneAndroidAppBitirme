package com.eczane.eczanebitirme.fragments;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.robolectric.util.FragmentTestUtil.startFragment;

/**
 * UnitTest for SearchFragment Class
 *
 * @author Cehver V. Karakoc
 */

@RunWith(RobolectricTestRunner.class)
public class SearchFragmentUnitTest {
    private Context context;
    private View view;

    @Before
    public void initFragment() {
        context = RuntimeEnvironment.application;
        SearchFragment fragment = new SearchFragment();
        startFragment(fragment);

        view = fragment.getView();
    }

    @Test
    public void clickingFindNearestButton_shouldOpenListViewActivity(){
        Button nearestButton = (Button) view.findViewById(R.id.button_nearest);
        nearestButton.performClick();

        Intent expectedIntent = new Intent(context,ListViewActivity.class);
        Intent observedIntent = ShadowApplication.getInstance().getNextStartedActivity();

        assertThat("List View doesn't Open",observedIntent.getComponent(), is(expectedIntent.getComponent()));
    }

    @Test
    public void searchEditorAction_shouldOpenListViewActivity(){
        EditText searchInput = (EditText) view.findViewById(R.id.edit_text_search);

        searchInput.setText("TEST_OPEN");
        searchInput.onEditorAction(EditorInfo.IME_ACTION_SEARCH);

        Intent expectedIntent = new Intent(context,ListViewActivity.class);
        Intent observedIntent = ShadowApplication.getInstance().getNextStartedActivity();

        assertThat("List View doesn't Open",observedIntent.getComponent(), is(expectedIntent.getComponent()));
    }

    @Test
    public void searchEditorAction_shouldAddSearchRecord(){
        EditText searchInput = (EditText) view.findViewById(R.id.edit_text_search);

        SearchRecord expectedRecord = new SearchRecord("TEST_RECORD");

        searchInput.setText(expectedRecord.getKey());
        searchInput.onEditorAction(EditorInfo.IME_ACTION_SEARCH);

        SearchRecord observedRecord = new Storage(context,"Eczane").getLastSearches().get(0);

        assertThat("Keys are not same",observedRecord.getKey(), is(expectedRecord.getKey()));
    }
}
