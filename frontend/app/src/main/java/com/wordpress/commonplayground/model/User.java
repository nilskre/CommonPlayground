package com.wordpress.commonplayground.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private Long id;
    private String name;

    public User(String name) {
        this.name = name;
    }

    public User(Parcel in) {
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

    public void readFromParcel(Parcel in) {
        name = in.readString();
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
