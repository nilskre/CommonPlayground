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
import com.wordpress.commonplayground.BuildConfig;
import com.wordpress.commonplayground.model.Message;
import com.wordpress.commonplayground.model.Session;
import com.wordpress.commonplayground.network.VolleyRequestQueue;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MessagesViewModel extends AndroidViewModel {

    private MutableLiveData<List<Message>> inbox;

    public MessagesViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Message>> getMessages(){
        if(inbox == null){
            inbox = new MutableLiveData<List<Message>>();
        }
        getMessagesFromServer();
        return inbox;
    }

    private void getMessagesFromServer() {
        String url = BuildConfig.SERVER_URL + "getMyMessages";
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<Message> allMessagesTmpList = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                Message message = Message.parseJoinMessage(response.getJSONObject(i));
                                allMessagesTmpList.add(i, message);
                            } catch (JSONException e) {
                                Log.d("Parse.Error.Main", e.toString());
                            }
                        }
                        inbox.setValue(allMessagesTmpList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Parse.Response", String.valueOf(error));
                    }
                }
        );
        VolleyRequestQueue.getInstance(this.getApplication()).addToQueue(getRequest, "Get user inbox");
    }
}