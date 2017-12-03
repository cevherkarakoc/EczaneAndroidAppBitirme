package com.eczane.eczanebitirme.adapters.Search;

import com.eczane.eczanebitirme.models.SearchRecord;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * UnitTest for SearchAdapter Class
 *
 * @author Cehver V. Karakoc
 */

@RunWith(RobolectricTestRunner.class)
public class SearchAdapterUnitTest {
    private final String KEY_ONE = "KEY_ONE";
    private final String KEY_TWO = "KEY_TWO";
    private SearchAdapter adapter;

    @Before
    public void initAdapter(){
        ArrayList<SearchRecord> records = new ArrayList<>();

        records.add(new SearchRecord(KEY_ONE));
        records.add(new SearchRecord(KEY_TWO));

        adapter = new SearchAdapter(records);
    }

    @Test
    public void testGetCount(){
        int count = adapter.getItemCount();
        int expectedCount = 2;

        assertThat("Wrong length", count, is(expectedCount));
    }

    @Test
    public void testGetItem(){
        String keyOne = adapter.getItem(0).getKey();
        String keyTwo = adapter.getItem(1).getKey();

        assertThat("First Key Wrong", keyOne, is(KEY_ONE));
        assertThat("Second Key Wrong", keyTwo, is(KEY_TWO));
    }
}
