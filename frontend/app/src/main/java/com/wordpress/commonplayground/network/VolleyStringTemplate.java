package com.wordpress.commonplayground.network;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.wordpress.commonplayground.BuildConfig;
import com.wordpress.commonplayground.R;

import java.util.HashMap;
import java.util.Map;

public abstract class VolleyStringTemplate {
    Resources r;

    public void stringRequest(String api, String tag, Context context, HashMap<String, String> parameters, View view) {
        String url = BuildConfig.SERVER_URL + api;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response." + tag, response);
                handleString(response, view);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error." + tag, String.valueOf(error));
                Snackbar.make(view, r.getString(R.string.new_error), 5000)
                        .setAction("Action", null).show();
            }
        }) {
            protected Map<String, String> getParams() {
                return parameters;
            }
        };
        VolleyRequestQueue.getInstance(context).addToQueue(stringRequest, tag);
    }

    protected abstract void handleString(String response, View view);


}
