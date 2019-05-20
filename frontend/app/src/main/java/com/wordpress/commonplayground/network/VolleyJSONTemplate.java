package com.wordpress.commonplayground.network;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.wordpress.commonplayground.BuildConfig;

import org.json.JSONArray;

import java.util.List;

public abstract class VolleyJSONTemplate {


    public void getJSONRequest(String api, String tag, Context context, MutableLiveData<List<?>> list) {
        String url = BuildConfig.SERVER_URL + api;
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                handleJSON(response, list);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error." + tag, String.valueOf(error));
                    }
                }
        );
        VolleyRequestQueue.getInstance(context).addToQueue(getRequest, tag);
    }

    public abstract void handleJSON(JSONArray response, MutableLiveData<List<?>> list);

}
