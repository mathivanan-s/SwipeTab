package com.example.mathi.swipetab.firebase;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Android on 5/3/2017.
 */

public class CustomFirebaseInstanceIdSercice extends FirebaseInstanceIdService {
    private final static String REG_TOKEN = "REG_TOKEN";

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(REG_TOKEN, token);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("firebase_details", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("firebase_token", token);
        editor.apply();

    }
}
