package com.eczane.eczanebitirme.helpers;

import com.eczane.eczanebitirme.models.Pharmacy;
import com.eczane.eczanebitirme.models.SearchRecord;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Holder for Types
 *
 * @author Cehver V. Karakoc
 */
class Typer {
    static final Type SEARCH_RECORD_ARRAYLIST = new TypeToken<ArrayList<SearchRecord>>(){}.getType();
    static final Type PHARMANCY_HASH_MAP = new TypeToken<HashMap<String, Pharmacy>>(){}.getType();
}
