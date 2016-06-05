package com.example.yora.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yora.R;

public class LoginFragment extends BaseFragment implements View.OnClickListener {
    private View _loginButton;
    private Callbacks _callbacks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        _loginButton = view.findViewById(R.id.fragment_login_loginButton);
        _loginButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == _loginButton) {
            application.getAuth().getUser().setLoggedIn(true);
            application.getAuth().getUser().setDisplayName("Dariush Lotfi");
            _callbacks.onLoggedIn();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        _callbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _callbacks = null;
    }

    // Observer Pattern
    public interface Callbacks {
        void onLoggedIn();
    }
}
