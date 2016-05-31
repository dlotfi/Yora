package com.example.yora.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yora.infrastructure.YoraApplication;

public abstract class BaseActivity extends AppCompatActivity {
    protected YoraApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (YoraApplication) getApplication();
    }
}
