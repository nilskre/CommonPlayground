package com.wordpress.commonplayground.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyRequestQueue {
    private static VolleyRequestQueue volleyNetworkRequestQueue;
    private RequestQueue volleyRequestQueue;

    private VolleyRequestQueue(Context context) {
        volleyRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static VolleyRequestQueue getInstance(Context context) {
        if (volleyNetworkRequestQueue == null) {
            synchronized (VolleyRequestQueue.class) {
                if (volleyNetworkRequestQueue == null) {
                    volleyNetworkRequestQueue = new VolleyRequestQueue(context);
                }
            }
        }
        return volleyNetworkRequestQueue;
    }

    public void addToQueue(Request request, String tag) {
        request.setTag(tag);
        volleyRequestQueue.add(request);
    }

    public void cancelAll(String tag) {
        volleyRequestQueue.cancelAll(tag);
    }
}