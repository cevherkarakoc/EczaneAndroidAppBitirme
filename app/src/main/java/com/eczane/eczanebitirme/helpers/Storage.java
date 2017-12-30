package com.eczane.eczanebitirme.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.eczane.eczanebitirme.models.Pharmacy;
import com.eczane.eczanebitirme.models.SearchRecord;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A helper for SharedPreferences
 *
 * @author Cehver V. Karakoc
 */
public class Storage {
    private static final String LAST_SEARCHES = "last_searches";
    private static final String FAVORITES = "favorites";
    private static final String CACHE = "cache";

    private SharedPreferences sharedPref;
    private Gson gson;

    public Storage (Context c, String name){
        sharedPref = c.getSharedPreferences(name,Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public ArrayList<SearchRecord> getLastSearches() {
        String lastSearchesJson = sharedPref.getString(LAST_SEARCHES,null);
        if(lastSearchesJson == null){
            return new ArrayList<>();
        }
        return gson.fromJson(lastSearchesJson,Typer.SEARCH_RECORD_ARRAYLIST);
    }

    public void clearLastSearches() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(LAST_SEARCHES);
        editor.apply();
    }

    public void addLastSearch(String search) {
        ArrayList<SearchRecord> newSearches = getLastSearches();
        newSearches.add(0,new SearchRecord(search));

        int lastIndex = newSearches.size() - 1;
        if(lastIndex > 4) newSearches.remove(lastIndex);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(LAST_SEARCHES,gson.toJson(newSearches));
        editor.apply();
    }

    public HashMap<String, Pharmacy> getFavorites() {
        String favoritesJson = sharedPref.getString(FAVORITES,null);

        if(favoritesJson == null){
            return new HashMap<>();
        }
        return gson.fromJson(favoritesJson,Typer.PHARMANCY_HASH_MAP);
    }

    public Pharmacy getFavoritesByKey(String key) {
        HashMap favorites = getFavorites();

        return (Pharmacy) favorites.get(key);
    }

    public void addFavorites(Pharmacy pharmacy) {
        HashMap favorites = getFavorites();
        favorites.put(""+pharmacy.getID(), pharmacy.clone());

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(FAVORITES,gson.toJson(favorites));
        editor.apply();
    }

    public void removeFavorites(Pharmacy pharmacy) {
        HashMap favorites = getFavorites();
        favorites.remove(""+pharmacy.getID());

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(FAVORITES,gson.toJson(favorites));
        editor.apply();
    }

    public void cachePharmancies(Pharmacy[] pharmacies){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(CACHE,gson.toJson(pharmacies));
        editor.apply();
    }

    public Pharmacy[] getPharmancies(){
        String pharmaciesJson = sharedPref.getString(CACHE,null);
        if(pharmaciesJson == null){
            return new Pharmacy[]{};
        }
        return gson.fromJson(pharmaciesJson,Pharmacy[].class);
    }

}
