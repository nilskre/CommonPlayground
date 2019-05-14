package com.wordpress.commonplayground.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Session implements Parcelable {

    private Long id;
    private String title;
    private String description;
    private String game;
    private String place;
    private String date;
    private String time;
    private int numberOfPlayers;
    private Long idOfHost;
    private String genre;
    private String isOnline;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<User> usersPending = new ArrayList<>();


    public Session(String title, String description, String game, String place, String date, String time, int numberOfPlayers, Long sessionId, String genre, String isOnline, ArrayList<User> users, ArrayList<User> usersPending) {
        this.title = title;
        this.description = description;
        this.game = game;
        this.place = place;
        this.date = date;
        this.time = time;
        this.numberOfPlayers = numberOfPlayers;
        this.id = sessionId;
        this.genre = genre;
        this.isOnline = isOnline;
        this.users.addAll(users);
        this.usersPending.addAll(usersPending);
    }

    public Session(Parcel in) {
        super();
        readFromParcel(in);
    }

    public static final Parcelable.Creator<Session> CREATOR = new Parcelable.Creator<Session>() {
        public Session createFromParcel(Parcel in) {
            return new Session(in);
        }

        public Session[] newArray(int size) {
            return new Session[size];
        }
    };

    private void readFromParcel(Parcel in) {
        title = in.readString();
        description = in.readString();
        game = in.readString();
        place = in.readString();
        date = in.readString();
        time = in.readString();
        numberOfPlayers = in.readInt();
        id = in.readLong();
        genre = in.readString();
        isOnline = in.readString();
        users = in.readArrayList(User.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(game);
        dest.writeString(place);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeInt(numberOfPlayers);
        dest.writeLong(id);
        dest.writeString(genre);
        dest.writeString(isOnline);
        dest.writeList(users);
    }

    public static Session parseSession(JSONObject sessionObject) {
        ArrayList users = new ArrayList();
        try {
            JSONArray parsedUsers = sessionObject.getJSONArray("users");
            for (int i = 0; i < parsedUsers.length(); i++) {
                JSONObject user = parsedUsers.getJSONObject(i);
                Long id = Long.valueOf(user.getString("id"));
                String username = user.getString("username");
                String email = user.getString("email");
                users.add(new User(id, username, email));
            }
            ArrayList usersPending = new ArrayList();
            JSONArray parsedPenders = sessionObject.getJSONArray("usersPending");
            for (int i = 0; i < parsedPenders.length(); i++) {
                JSONObject user = parsedPenders.getJSONObject(i);
                Long id = Long.valueOf(user.getString("id"));
                String username = user.getString("username");
                String email = user.getString("email");
                usersPending.add(new User(id, username, email));
            }

            Session parsed = new Session(sessionObject.getString("title"), sessionObject.getString("description"), sessionObject.getString("game"), sessionObject.getString("place"), sessionObject.getString("date"), sessionObject.getString("time"), sessionObject.getInt("numberOfPlayers"), sessionObject.getLong("id"),sessionObject.getString("genre"), sessionObject.getString("isOnline"), users, usersPending);
            Log.v("PARSED", "ID: " + parsed.getId() + " " + parsed.toString());
            return parsed;

        } catch (JSONException e) {
            Log.d("Parse.Session", e.toString());
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGame() {
        return game;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }

    public String getTime() { return time; }

    public String getGenre() { return genre; }

    public String getType() { return isOnline; }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        String s = "Session id=" + id + "Session title=" + title + " description=" + description + " game=" + game + " genre= "+ genre + " type=" + isOnline + " place=" + place + " date=" + date + " time=" + time + " numberOfPlayers=" + numberOfPlayers + " users=";
        for(User user : users) {
            s += user.getName() + " ";
        }
        return s;
    }
}