package com.eczane.eczanebitirme.models;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by cevhe on 31.10.2017.
 */

public class SearchRecord {
    private String key;
    private Date date;

    public SearchRecord(String key) {
        this.key = key;
        date = Calendar.getInstance().getTime();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return date.toString() +":"+ key;
    }
}
