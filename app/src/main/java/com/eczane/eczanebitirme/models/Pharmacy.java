package com.eczane.eczanebitirme.models;

/**
 * Created by cevhe on 22.10.2017.
 */

public class Pharmacy {
    private String title;
    private String phone;
    private Zone province;
    private Zone district;
    private Address address;
    private boolean sentry = false;

    public Pharmacy (String title, String phone, Zone province, Zone district, Address address ) {
        this.title = title;
        this.phone = phone;
        this.province = province;
        this.district = district;
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public String getPhone() {
        return phone;
    }

    public Zone getProvince() {
        return province;
    }

    public Zone getDistrict() {
        return district;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isSentry () { return sentry; }
}
