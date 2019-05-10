package com.wordpress.commonplayground.network;

import android.util.Log;

public class VolleyStringNoResponse extends VolleyStringTemplate {


    @Override
    protected void handleString(String response) {
        Log.d("Response.unhandled", response);
    }
}
