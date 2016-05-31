package com.example.yora.infrastructure;

import android.app.Application;

public class YoraApplication extends Application {
    private Auth _auth;

// It's also correct; but because object has not been completely initiallized at this point,
// it may cause problem in Auth.
//    public YoraApplication() {
//        _auth = new Auth(this);
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        _auth = new Auth(this);
    }

    public Auth getAuth() {
        return _auth;
    }
}
