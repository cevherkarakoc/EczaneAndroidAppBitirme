package com.eczane.eczanebitirme.helpers.HTTPRequest;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.eczane.eczanebitirme.models.Pharmacy;

/**
 * @author Cehver V. Karakoc
 */
public class RequestHandler {
    private static String pharURL = "http://php.ceveka.com/pharmancies.json";

    public static void fetchPharmaciesList(Context context, Response.Listener<Pharmacy[]> successListener, Response.ErrorListener errorListener) {
        RequestQueue queue = Volley.newRequestQueue(context);

        GsonRequest<Pharmacy[]> req = new GsonRequest<>(
                Request.Method.GET,
                pharURL,
                Pharmacy[].class,
                successListener,
                errorListener
        );
        queue.add(req);
    }
}
