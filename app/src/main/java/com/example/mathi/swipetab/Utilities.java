package com.example.mathi.swipetab;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Android on 5/2/2017.
 */

public class Utilities {

    public static String getFirebaseToken(Activity activity){
        String USER_DETAILS_PREFS_NAME = "firebase_details";
        SharedPreferences settings = activity.getSharedPreferences(USER_DETAILS_PREFS_NAME, MODE_PRIVATE);
        return settings.getString("firebase_token", "no_token");
    }
}

