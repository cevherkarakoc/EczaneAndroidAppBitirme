package com.eczane.eczanebitirme.models;

/**
 * Created by cevhe on 22.10.2017.
 */

public class Address {
    private String text;
    private double x;
    private double y;

    public Address (String text, double x, double y) {
        this.text = text;
        this.x = x;
        this.y = y;
    }


    public String getText() {
        return text;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
