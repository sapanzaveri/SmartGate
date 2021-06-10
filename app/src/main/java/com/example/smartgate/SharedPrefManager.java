package com.example.smartgate;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.smartgate.Model.User;

public class SharedPrefManager {

    private static String SHARED_PREF_MANAGER = "smartlogin";
    private SharedPreferences sharedPreferences;
    Context context;
    private SharedPreferences.Editor editor;

    public SharedPrefManager(Context context) {
        this.context = context;
    }

    public void saveUser(User user) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_MANAGER, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("Id", user.getId());
        editor.putString("Name", user.getName());
        editor.putString("PhoneNo", user.getPhone_No());
        editor.putString("User_Name", user.getUser_Name());
        editor.putBoolean("Logged", true);
        editor.apply();
    }

    public boolean isLoggedIn() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_MANAGER, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("Logged", false);
    }

    public User getUser() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_MANAGER, Context.MODE_PRIVATE);
        return new User(sharedPreferences.getInt("Id", -1),
                sharedPreferences.getString("Name", null),
                sharedPreferences.getString("User_Name", null),
                sharedPreferences.getString("PhoneNo", null));
    }

    public void logout() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_MANAGER, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
