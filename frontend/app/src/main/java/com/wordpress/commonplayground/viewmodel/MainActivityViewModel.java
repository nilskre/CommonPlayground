package com.wordpress.commonplayground.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.wordpress.commonplayground.model.Session;
import com.wordpress.commonplayground.network.VolleyRequestQueue;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<Session>> activeSessions;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Session>> getSessions(){
        if(activeSessions == null){
            activeSessions = new MutableLiveData<List<Session>>();
        }
        getSessionsFromServer();
        return activeSessions;
    }

    private void getSessionsFromServer() {
        String url = "http://10.0.2.2:8080/getSessionList";
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<Session> allSessionsTmpList = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                Session session = Session.parseSession(response.getJSONObject(i));
                                allSessionsTmpList.add(i, session);
                                Log.d("Received Sessions", session.toString());
                            } catch (JSONException e) {
                                Log.d("Parse.Error.Main", e.toString());
                            }
                        }
                        activeSessions.setValue(allSessionsTmpList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Parse.Response", String.valueOf(error));
                    }
                }
        );
        VolleyRequestQueue.getInstance(this.getApplication()).addToQueue(getRequest, "Get all Sessions");
    }
}