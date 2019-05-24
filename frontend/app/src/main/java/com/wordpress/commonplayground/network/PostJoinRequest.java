package com.wordpress.commonplayground.network;

import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.wordpress.commonplayground.R;

import java.util.HashMap;

public class PostJoinRequest extends VolleyStringTemplate {

    public PostJoinRequest(Resources r) {
        super();
    }

    @Override
    protected void handleString(String response, View view) {
        Log.d("JOIN", response);
        HashMap<Integer,String> snackText = new HashMap <Integer, String> (){{
            put (-11, view.getResources().getString(R.string.join_error_joined));
            put (-10, view.getResources().getString(R.string.join_error_full));
            put (0, view.getResources().getString(R.string.join_success));
        }};
            Snackbar.make(view, snackText.get(Integer.parseInt(response)), 5000).show();
        }
}
