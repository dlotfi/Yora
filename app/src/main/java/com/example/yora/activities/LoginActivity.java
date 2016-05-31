package com.example.yora.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.yora.R;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private View _loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _loginButton = findViewById(R.id.activity_login_login);
        if (_loginButton != null) {
            _loginButton.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == _loginButton) {
            startActivity(new Intent(this, LoginNarrowActivity.class));
        }
    }
}
