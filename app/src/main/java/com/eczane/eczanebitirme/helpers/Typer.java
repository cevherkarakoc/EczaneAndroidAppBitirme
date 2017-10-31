package com.eczane.eczanebitirme.helpers;

import com.eczane.eczanebitirme.models.SearchRecord;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Holder for Types
 *
 * @author Cehver V. Karakoc
 */
class Typer {
    static final Type SEARCH_RECORD_ARRAYLIST = new TypeToken<ArrayList<SearchRecord>>(){}.getType();
}
