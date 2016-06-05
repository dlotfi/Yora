package com.example.yora.activities;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.yora.infrastructure.YoraApplication;
import com.example.yora.R;
import com.example.yora.views.NavDrawer;

public abstract class BaseActivity extends AppCompatActivity {
    protected YoraApplication application;
    protected Toolbar toolbar;
    protected NavDrawer navDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (YoraApplication) getApplication();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        toolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (toolbar != null)
            setSupportActionBar(toolbar);
    }

    protected void setNavDrawer(NavDrawer navDrawer) {
        this.navDrawer = navDrawer;
        this.navDrawer.create();
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public YoraApplication getYoraApplication() {
        return application;
    }
}
