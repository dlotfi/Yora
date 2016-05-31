package com.example.yora.infrastructure;


import android.content.Context;

public class Auth {

    private final Context _context;
    private User _user;


    public Auth(Context context) {
        _context = context;
        _user = new User();
    }

    public User getUser() {
        return _user;
    }
}
