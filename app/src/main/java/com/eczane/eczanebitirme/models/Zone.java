package com.eczane.eczanebitirme.models;

/**
 * Zone Model Object
 *
 * @author Cehver V. Karakoc
 */
public class Zone {
    private int id;
    private String name;

    public Zone(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
