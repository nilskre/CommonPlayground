package com.wordpress.commonplayground.network;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.wordpress.commonplayground.model.Session;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class GetSessionRequest extends VolleyJSONTemplate {

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
        list.setValue(allSessionsTmpList);
    }
}
