package com.wordpress.commonplayground.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private Long id;
    private String name;
    private String email;

    User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    private User(Parcel in) {
        super();
        readFromParcel(in);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private void readFromParcel(Parcel in) {
        id = in.readLong();
        name = in.readString();
        email = in.readString();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(email);
    }
}
