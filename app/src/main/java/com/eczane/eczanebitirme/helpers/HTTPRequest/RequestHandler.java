package com.eczane.eczanebitirme.helpers.HTTPRequest;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.eczane.eczanebitirme.models.Pharmacy;
import com.eczane.eczanebitirme.models.Zone;

/**
 * @author Cehver V. Karakoc
 */
public class RequestHandler {
    private static String pharURL = "http://724eczanem.com/eczaneler_json.php";
    private static String zoneURL = "http://724eczanem.com/ilce_id_from_posta.php?posta_kodu=";

    public static void fetchPharmaciesList(Context context, Response.Listener<Pharmacy[]> successListener, Response.ErrorListener errorListener,String keyword) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = pharURL + "?keyword="+keyword;

        GsonRequest<Pharmacy[]> req = new GsonRequest<>(
                Request.Method.GET,
                url,
                Pharmacy[].class,
                successListener,
                errorListener
        );
        queue.add(req);
    }

    public static void fetchPharmaciesList(Context context, Response.Listener<Pharmacy[]> successListener, Response.ErrorListener errorListener,double lat, double lng) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = pharURL + "?lat="+lat+"&lng="+lng;
        Log.d("REQ", url);
        GsonRequest<Pharmacy[]> req = new GsonRequest<>(
                Request.Method.GET,
                url,
                Pharmacy[].class,
                successListener,
                errorListener
        );
        queue.add(req);
    }

    public static void fetchZoneID(Context context, Response.Listener<Zone> successListener, Response.ErrorListener errorListener, String postalCode){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = zoneURL + postalCode;
        GsonRequest<Zone> req = new GsonRequest<>(
                Request.Method.GET,
                url,
                Zone.class,
                successListener,
                errorListener
        );
        queue.add(req);
    }
}
