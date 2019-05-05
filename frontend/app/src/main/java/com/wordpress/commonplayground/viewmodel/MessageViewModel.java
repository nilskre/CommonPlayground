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
import com.android.volley.toolbox.StringRequest;
import com.wordpress.commonplayground.BuildConfig;
import com.wordpress.commonplayground.model.Message;
import com.wordpress.commonplayground.network.VolleyRequestQueue;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageViewModel extends AndroidViewModel {

    private MutableLiveData<List<Message>> inbox;

    public MessageViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Message>> getMessage(String userID){
        if(inbox == null){
            inbox = new MutableLiveData<List<Message>>();
        }
        getMessagesFromServer(userID);
        return inbox;
    }

    private void getMessagesFromServer(String userID) {
        String url = BuildConfig.SERVER_URL + "getMyMessages?userID=" + userID;
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<Message> allMessagesTmpList = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                //TODO: Find out which kind of message and parse correctly
                                Message message = Message.parseJoinMessage(response.getJSONObject(i));
                                allMessagesTmpList.add(i, message);
                                Log.d("Received Sessions", message.toString());
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
        VolleyRequestQueue.getInstance(this.getApplication()).addToQueue(getRequest, "Get all Messages");
    }

    public void deleteMessage(String userID, String messageID) {
        String url = BuildConfig.SERVER_URL + "removeMessage";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", String.valueOf(error));
                //Snackbar.make(view, R.string.new_error, 5000)
                //        .setAction("Action", null).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("userID", userID);
                MyData.put("messageID", messageID);
                return MyData;
            }
        };
        VolleyRequestQueue.getInstance(this.getApplication()).addToQueue(postRequest, "Get all Messages");
    }
}