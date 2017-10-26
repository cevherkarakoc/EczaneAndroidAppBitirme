package com.eczane.eczanebitirme.models;

/**
 * Created by cevhe on 22.10.2017.
 */

public class Dummy {
    public static Zone hatay = new Zone(31, "Hatay");

    public static Zone iskenderun = new Zone(1, "İskenderun");
    public static Zone payas = new Zone(2, "Payas");

    public static Address address = new Address("Mahalle sokak no: 1001", 36.579739, 36.161361);
    public static Address address2 = new Address("Mahalle2 sokak2 no: 1002", 36.760249, 36.200663);

    public static Pharmacy mehmetEczane = new Pharmacy("Mehmet Eczane", "+90 537 825 0895", hatay, iskenderun, address);
    public static Pharmacy cevherEczane = new Pharmacy("Cevher Eczane", "+90 590 999 1122", hatay, payas, address2);
    public static Pharmacy ikramEczane = new Pharmacy("İkram Eczane", "+90 777 333 4411", hatay, payas, address);

    public static Pharmacy[] alist = {mehmetEczane,cevherEczane,ikramEczane,ikramEczane,mehmetEczane,cevherEczane};
}
