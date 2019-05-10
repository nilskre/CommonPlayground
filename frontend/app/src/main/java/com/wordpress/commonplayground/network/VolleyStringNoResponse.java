package com.wordpress.commonplayground.network;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.wordpress.commonplayground.BuildConfig;

import java.util.HashMap;
import java.util.Map;

public class VolleyStringNoResponse extends VolleyStringTemplate {

    public void stringRequest(String api, String tag, Context context, HashMap<String, String> parameters) {
        String url = BuildConfig.SERVER_URL + api;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response." + tag, response);
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


    @Override
    protected void handleString(String response, View view) {
        Log.d("Response.unhandled", response);
    }
}
