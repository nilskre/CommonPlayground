package com.wordpress.commonplayground.network;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.wordpress.commonplayground.model.Session;
import com.wordpress.commonplayground.view.SearchResultActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class GetSearchResultRequest extends VolleyJSONTemplate {
    private final Activity activity;

    public GetSearchResultRequest(Activity activity) {
        super();
        this.activity = activity;
    }

    @Override
    public void handleJSON(JSONArray response, MutableLiveData<List<?>> list) {
        List<Session> allSessionsTmpList = new ArrayList<>();
        Log.d("Received Sessions", ""+response.length());
        for (int i = 0; i < response.length(); i++) {
            try {
                Session session = Session.parseSession(response.getJSONObject(i));
                allSessionsTmpList.add(i, session);
            } catch (JSONException e) {
                Log.d("Parse.Error.Main", e.toString());
            }
        }

        if (allSessionsTmpList.size() == 0) {
            Snackbar.make(activity.getWindow().getDecorView().findViewById(android.R.id.content), "Couldn't find any sessions", 2000).show();
        } else {
            Intent openSearchResultActivity = new Intent(activity.getApplicationContext(), SearchResultActivity.class);
            Bundle b = new Bundle();
            b.putParcelableArrayList("Sessions", (ArrayList<? extends Parcelable>) allSessionsTmpList);
            openSearchResultActivity.putExtras(b);
            activity.startActivity(openSearchResultActivity);
        }
    }
}