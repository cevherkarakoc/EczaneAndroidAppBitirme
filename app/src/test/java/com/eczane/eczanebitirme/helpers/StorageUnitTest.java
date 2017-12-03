package com.eczane.eczanebitirme.helpers;

import com.eczane.eczanebitirme.models.SearchRecord;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * UnitTest for Storage Class
 *
 * @author Cehver V. Karakoc
 */
@RunWith(RobolectricTestRunner.class)
public class StorageUnitTest{
    private final static String STORAGE_KEY = "TEST_STORAGE";

    private Storage testStorage;
    @Before
    public void createStorage(){
        testStorage = new Storage(RuntimeEnvironment.application,STORAGE_KEY);
    }

    @Test
    public void storage_AddSearchRecord(){
        String firstRecord = "Test Search1";
        String lastRecord = "Test Search2";

        testStorage.addLastSearch(firstRecord);
        testStorage.addLastSearch(lastRecord);

        ArrayList<SearchRecord> lastSearches = testStorage.getLastSearches();

        assertThat(lastSearches.size(), is(2));
        assertThat(lastSearches.get(0).getKey(), is(lastRecord));
    }

    @Test
    public void storage_GetSearchRecords(){
        ArrayList<SearchRecord> lastSearches = testStorage.getLastSearches();

        assertThat(lastSearches.size(), is(0));
    }

    @Test
    public void storage_MaxSizeSearches(){
        for(int i=0;i<10;i++){
            testStorage.addLastSearch("test"+i);
        }

        ArrayList<SearchRecord> lastSearches = testStorage.getLastSearches();

        assertThat(lastSearches.size(), is(5));
    }

    @Test
    public void storage_ClearSearches(){
        for(int i=0;i<2;i++){
            testStorage.addLastSearch("test"+i);
        }
        testStorage.clearLastSearches();

        ArrayList<SearchRecord> lastSearches = testStorage.getLastSearches();

        assertThat(lastSearches.size(), is(0));
    }
}
