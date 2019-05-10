package com.wordpress.commonplayground.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.wordpress.commonplayground.BuildConfig;

import java.util.HashMap;
import java.util.Map;

public abstract class VolleyStringTemplate {

    public void stringRequest(String api, String tag, Context context, HashMap<String, String> parameters) {
        String url = BuildConfig.SERVER_URL + api;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response." + tag, response);
                handleString(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error." + tag, String.valueOf(error));
            }
        }) {
            protected Map<String, String> getParams() {
                return parameters;
            }
        };
        VolleyRequestQueue.getInstance(context).addToQueue(stringRequest, tag);
    }

    public abstract void handleString(String response);

}
