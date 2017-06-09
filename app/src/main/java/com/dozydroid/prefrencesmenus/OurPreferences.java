package com.dozydroid.prefrencesmenus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by MIRSAAB on 6/9/2017.
 */

public class OurPreferences {

    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared Pref Name
    private final static String PREF_NAME = "PreferencesMenus";

    // Keys for storing and fetching values
    public final static String KEY_USERNAME = "username";
    public final static String KEY_PASSWORD = "password";
    public final static String KEY_LOGIN = "isLogin";


    public OurPreferences(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void saveLogin(String userName, String password){
        editor.putString(KEY_USERNAME, userName);
        editor.putString(KEY_PASSWORD, password);
        editor.putBoolean(KEY_LOGIN, true);
        editor.commit();
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> userDetails = new HashMap<>();
        userDetails.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));
        userDetails.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
        return userDetails;
    }

    public boolean checkLogin(){
        boolean isLogin = pref.getBoolean(KEY_LOGIN, false);
        return isLogin;
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent intent = new Intent(_context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(intent);
    }


}
