package com.eczane.eczanebitirme.models;

/**
 * Pharmancy Model Object
 *
 * @author Cehver V. Karakoc
 */
public class Pharmacy {
    private int id;
    private String title;
    private String phone;
    private Zone province;
    private Zone district;
    private Address address;
    private boolean sentry = false;
    private double dist = 0;

    public Pharmacy (String title, String phone, Zone province, Zone district, Address address ) {
        this.title = title;
        this.phone = phone;
        this.province = province;
        this.district = district;
        this.address = address;
    }

    public int getID() {return id;}

    public void setID(int id) {this.id = id;}

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

    public String getZoneAsString() {
        return this.getDistrict().getName() + ", " + this.getProvince().getName();
    }

    public Address getAddress() {
        return address;
    }

    public boolean isSentry () { return sentry; }

    public double getDist() {return dist;}

    public void setDist(double dist) {this.dist = dist;}

    public Pharmacy clone() {
        Pharmacy pharmacy = new Pharmacy(title,phone,province,district,address);
        pharmacy.setID(this.id);

        return pharmacy;
    }
}
