package com.example.yora.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yora.R;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private Button _registerButton;
    private EditText _userNameText;
    private EditText _emailText;
    private EditText _passwordText;
    private View _progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        _registerButton = (Button) findViewById(R.id.activity_register_registerButton);
        _userNameText = (EditText) findViewById(R.id.activity_register_userName);
        _emailText = (EditText) findViewById(R.id.activity_register_email);
        _passwordText = (EditText) findViewById(R.id.activity_register_password);
        _progressBar = findViewById(R.id.activity_register_progressBar);

        _registerButton.setOnClickListener(this);
        _progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        if (view == _registerButton) {
            _progressBar.setVisibility(View.VISIBLE);
            application.getAuth().getUser().setLoggedIn(true);
            setResult(RESULT_OK);
            finish();
        }
    }
}
