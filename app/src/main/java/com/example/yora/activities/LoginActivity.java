package com.example.yora.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.yora.R;
import com.example.yora.fragments.LoginFragment;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginFragment.Callbacks {
    private static final int REQUEST_NARROW_LOGIN = 1;
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
            startActivityForResult(new Intent(this, LoginNarrowActivity.class), REQUEST_NARROW_LOGIN);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;

        if (requestCode == REQUEST_NARROW_LOGIN)
            finishLogin();
    }

    private void finishLogin() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onLoggedIn() {
        finishLogin();
    }
}
