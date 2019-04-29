package com.wordpress.commonplayground.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Message implements Parcelable {

    private String title;
    private String description;
    private Long id;
    private Long requesterID;
    private Long requestedSessionID;
    private Boolean read;

    public Message(String title, String description, Long id, Long requesterID, Long requestedSessionID){
        this.title = title;
        this.description = description;
        this.id = id;
        this.read = false;
        this.requestedSessionID = requestedSessionID;
        this.requesterID = requesterID;
    }

    public Message(String title, String description, Long id) {
        this.title = title;
        this.description = description;
        this.id = id;
    }

    public Message(Parcel in) {
        super();
        readFromParcel(in);
    }

    public static final Parcelable.Creator<Message> CREATOR = new Parcelable.Creator<Message>() {
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    public void readFromParcel(Parcel in) {
        title = in.readString();
        description = in.readString();
        id = in.readLong();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeLong(id);
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

    public static Message parseGeneralMessage(JSONObject messageObject){
        try {
            return new Message(messageObject.getString("title"), messageObject.getString("description"), messageObject.getLong("id"));
        } catch (JSONException e) {
            Log.d("Parse.Session", e.toString());
        }
        return null;
    }

    @Override
    public String toString() {
        return ("Message: " + title);
    }
}
