package com.wordpress.commonplayground.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Message {

    private final String title;
    private final String description;
    private final Long id;
    private Long requesterID;
    private Long requestedSessionID;
    private Boolean read;

    public Message(String title, String description, Long id, Long requesterID, Long requestedSessionID){
        this.title = title;
        this.description = description;
        this.id = id;
        this.read = false;
        this.requestedSessionID = requesterID;
        this.requesterID = requesterID;
    }

    public Message(String title, String description, Long id) {
        this.title = title;
        this.description = description;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public Long getRequesterID() {
        return requesterID;
    }

    public Long getRequestedSessionID() {
        return requestedSessionID;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public static Message parseJoinMessage(JSONObject messageObject){
        try {
            return   new Message(messageObject.getString("title"), messageObject.getString("description"), messageObject.getLong("id"), messageObject.getLong("id"), messageObject.getLong("id"));
        } catch (JSONException e) {
        Log.d("Parse.Session", e.toString());
    }
    return null;
    }

    public static Message parseGeneralMesssage(JSONObject messageObject){
        try {
            return new Message(messageObject.getString("title"), messageObject.getString("description"), messageObject.getLong("id"));
        } catch (JSONException e) {
            Log.d("Parse.Session", e.toString());
        }
        return null;
    }
}
