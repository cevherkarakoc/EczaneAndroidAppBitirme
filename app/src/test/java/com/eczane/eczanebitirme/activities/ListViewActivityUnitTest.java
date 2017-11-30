package com.eczane.eczanebitirme.activities;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;

import com.eczane.eczanebitirme.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.hamcrest.CoreMatchers.is;
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

    @Before
    public void initActivity() {
        context = RuntimeEnvironment.application;
        activity = Robolectric.buildActivity(ListViewActivity.class)
                .create()
                .start()
                .resume()
                .visible()
                .get();
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
}
