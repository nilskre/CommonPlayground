package com.wordpress.commonplayground.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Message implements Parcelable {

    private String authorName;
    private String title;
    private String description;
    private Long id;
    private String type;
    private Long requesterID;
    private Long requestedSessionID;
    private Boolean seen;

    private Message(String type, String title, String description, String author, Long id, Long requesterID, Long requestedSessionID, boolean seen) {
        this.type = type;
        this.title = title;
        this.description = description;
        this.authorName = author;
        this.id = id;
        this.seen = seen;
        this.requestedSessionID = requestedSessionID;
        this.requesterID = requesterID;
    }

    private Message(String type, String title, String description, String author, Long id, boolean seen) {
        this.type = type;
        this.title = title;
        this.authorName = author;
        this.description = description;
        this.id = id;
        this.seen = seen;
    }

    private Message(Parcel in) {
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

    private void readFromParcel(Parcel in) {
        title = in.readString();
        description = in.readString();
        authorName = in.readString();
        id = in.readLong();
        type = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(authorName);
        dest.writeLong(id);
    }


    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return authorName;
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

    public Boolean isSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    public static Message parseJoinMessage(JSONObject messageObject){
        try {
            return (new Message(messageObject.getString("type"), messageObject.getString("title"), messageObject.getString("description"), messageObject.getString("authorName"),
                    messageObject.getLong("id"), messageObject.getLong("userIdWhoWantsToJoin"), messageObject.getLong("sessionIdUserWantsToJoin"), messageObject.getBoolean("seen")));
        } catch (JSONException e) {
        Log.d("Parse.Session", e.toString());
    }
    return null;
    }

    public static Message parseGeneralMessage(JSONObject messageObject){
        try {
            return new Message(messageObject.getString("type"), messageObject.getString("title"), messageObject.getString("description"), messageObject.getString("authorName"),
                    messageObject.getLong("id"), messageObject.getBoolean("seen"));
        } catch (JSONException e) {
            Log.d("Parse.Session", e.toString());
        }
        return null;
    }

    @NonNull
    @Override
    public String toString() {
        return ("Message: " + title);
    }
}
