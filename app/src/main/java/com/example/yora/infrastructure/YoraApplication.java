package com.example.yora.infrastructure;

import android.app.Application;
import android.net.Uri;

import com.example.yora.services.Module;
import com.squareup.otto.Bus;

public class YoraApplication extends Application {
    public static final Uri API_ENDPOINT = Uri.parse("http://yora-playground.3dbuzz.com");
    public static final String STUDENT_TOKEN = "8cf7c04452464b29b680ec836072b207";

    private Auth _auth;
    private Bus _bus;

    public YoraApplication() {
        // It's also correct; but because object has not been completely initiallized at this point,
        // it may cause problem in Auth.
        // _auth = new Auth(this);

        _bus = new Bus();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _auth = new Auth(this);
        Module.register(this);
    }

    public Auth getAuth() {
        return _auth;
    }

    public Bus getBus() {
        return _bus;
    }
}
