package com.wordpress.commonplayground.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.wordpress.commonplayground.view.LoginActivity;

import java.util.HashMap;

public class SessionManager {

    private final SharedPreferences pref;
    private final Editor editor;
    private final Context _context;

    private static final String PREF_NAME = "AndroidPref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ID = "UserID";
    public static final String KEY_MENU_ITEM_MAIN = "ActiveFragment";

    public SessionManager(Context context){
        this._context = context;
        int PRIVATE_MODE = 0;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.apply();
    }

    public void createLoginSession(String UID, String email){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID, UID);
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_ID, pref.getString(KEY_ID, null));
        user.put(KEY_MENU_ITEM_MAIN, pref.getString(KEY_MENU_ITEM_MAIN,"-1"));
        return user;
    }

    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();

        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        _context.startActivity(i);
    }

    private boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void setKeyMenuItemMain(String s) {
        editor.putString(KEY_MENU_ITEM_MAIN, s);
        editor.commit();
    }
}