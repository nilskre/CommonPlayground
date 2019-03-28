package com.wordpress.commonplayground.model;

import com.wordpress.commonplayground.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean isPasswordValid(String password) {
        String validPassword = "^([a-zA-Z0-9@*#!?$&.-_]{8,30})$";
        Matcher matcher = Pattern.compile(validPassword).matcher(password);
        return matcher.matches();
    }

    public static boolean isPasswordConfirmed(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }

    public static boolean isEmailValid(String email) {
        String validEmail = "[a-zA-Z0-9+._%\\-]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+";
        Matcher matcher = Pattern.compile(validEmail).matcher(email);
        return matcher.matches();
    }

}