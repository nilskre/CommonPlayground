package com.wordpress.commonplayground.model;

import android.content.Context;

import com.wordpress.commonplayground.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static boolean isPasswordValid(String password) {
        String validPassword = "^([a-zA-Z0-9@*#!?$&.-_]{8,30})$";
        Matcher matcher = Pattern.compile(validPassword).matcher(password);
        return matcher.matches();
    }

    public static boolean isPasswordConfirmed(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }

    private static boolean isEmailValid(String email) {
        String validEmail = "[a-zA-Z0-9+._%\\-]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+";
        Matcher matcher = Pattern.compile(validEmail).matcher(email);
        return matcher.matches();
    }

    public static String checkForValidPassword(String password, Context c) {
        String response = "";
        if (password.isEmpty()) {
            response = c.getString(R.string.error_field_required);
        } else if (password.length() < 8) {
            response = c.getString(R.string.error_short_password);
        } else if (password.length() > 30) {
            response = c.getString(R.string.error_long_password);
        } else if (!isPasswordValid(password)) {
            response = c.getString(R.string.error_invalid_password);
        }
        return response;
    }

    public static String checkForValidEmail(String email, Context c) {
        String response = "";
        if (email.isEmpty()) {
            response = c.getString(R.string.error_field_required);
        } else if (!isEmailValid(email)) {
            response = c.getString(R.string.error_invalid_email);
        }
        return response;
    }
}