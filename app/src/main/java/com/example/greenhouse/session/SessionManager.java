package com.example.greenhouse.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "GreenHouseAppSession";
    private static final String KEY_TOKEN = "jwtToken";

    private SharedPreferences sharedPreferences;

    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveAuthToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_TOKEN, token);
        editor.apply();
    }

    public String fetchAuthToken() {
        return sharedPreferences.getString(KEY_TOKEN, null);
    }

    public void clearAuthToken() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_TOKEN);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return fetchAuthToken() != null;
    }
}
