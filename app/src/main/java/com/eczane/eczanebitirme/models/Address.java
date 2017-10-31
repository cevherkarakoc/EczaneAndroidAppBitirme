package com.eczane.eczanebitirme.models;

/**
 * Address Model Object
 *
 * @author Cehver V. Karakoc
 */

public class Address {
    private String text;
    private double lat;
    private double lon;

    /**
     * Constructor.
     *
     * @param text (required) Text version of the address
     * @param lat (required) Latitude
     * @param lon (required) Longitude
     */
    public Address (String text, double lat, double lon) {
        this.text = text;
        this.lat = lat;
        this.lon = lon;
    }


    public String getText() {
        return text;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

}
