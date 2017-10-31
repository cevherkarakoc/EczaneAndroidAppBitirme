package com.eczane.eczanebitirme.helpers;

import com.eczane.eczanebitirme.models.SearchRecord;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by cevhe on 31.10.2017.
 */

class Typer {
    static final Type SEARCH_RECORD_ARRAYLIST = new TypeToken<ArrayList<SearchRecord>>(){}.getType();
}
