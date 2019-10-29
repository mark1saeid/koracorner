package com.example.koracorner;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

import com.google.firebase.FirebaseApp;

public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }
}
